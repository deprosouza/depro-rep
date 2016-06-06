package br.com.depro.fw.typezero.infrastructure.dao.helper;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.NoEntidadeBase;
import br.com.depro.fw.typezero.infrastructure.utils.FWCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypezeroCriteria extends NoEntidadeBase implements Serializable {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = 8519130949335324669L;
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";
	protected Class<?> persistenceClass;
	protected String alias;
	protected Integer limit = -1;
	protected Integer offset = -1;
	protected Boolean paged = Boolean.FALSE;
	protected Map<String, String> mapOrdenacao = new HashMap<String, String>();
	protected Map<String, Object> criterios = new HashMap<String, Object>();

	public TypezeroCriteria() {
		super();
	}
	
	public TypezeroCriteria(TypezeroCriteria criteria) {
		this.criterios = criteria.getCriterios();
		this.mapOrdenacao = criteria.getMapOrdenacao();
		this.limit = criteria.getLimit();
		this.offset = criteria.getOffset();
		this.paged = criteria.isPaged();
	}

	public TypezeroCriteria(String sort, String order) {
		super();
		addOrdenacao(sort, order);
	}

	public TypezeroCriteria(Integer limit, Integer offset, String sort, String order, Boolean paged) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.paged = paged;
		addOrdenacao(sort, order);
	}

	public boolean containsKey(String key) {
		return criterios.containsKey(key) && criterios.get(key) != null && StringUtils.isNotBlank(criterios.get(key).toString());
	}
	
	public Object getObject(String key) {
		if (containsKey(key)) {
			return criterios.get(key);
		}
		return null;
	}
	
	public String getString(String key) {
		return getString(key, false);
	}
	
	public String getString(String key, boolean isAccentOrCaseInsentive) {
		if (containsKey(key)) {
			return isAccentOrCaseInsentive ? Normalizer.normalize(getObject(key).toString().toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") : criterios.get(key).toString();
		}
		return null;
	}
	
	public BigDecimal getBigDecimal(String key) {
		if (containsKey(key)) {
			return new BigDecimal(getString(key));
		}
		return null;
	}
	
	public Long getLong(String key) {
	    if (StringUtils.isNotBlank(getString(key))) {
		    return Long.valueOf(getString(key));
	    }
	    return null;
	}
	
	public int getInt(String key) {
	    if (StringUtils.isNotBlank(getString(key))) {
		    return Integer.valueOf(getString(key));
	    }
	    return 0;
	}
	
	public Boolean getBoolean(String key) {
		return Boolean.valueOf(getString(key));
	}
	
	public void addCriterio(String chave, Object valor) {
		criterios.put(chave, valor);
	}
	
	public void addCriterioIsNullOrNotNull(String chave, boolean isNull) {
		criterios.put(chave + (isNull ? TypezeroCriterionUtils.IS_NULL : TypezeroCriterionUtils.NOT_NULL), true);
	}
	
	public void removeCriterio(String chave) {
		criterios.remove(chave);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListString(String key) {
		if (containsKey(key)) {
			return (List<String>)getObject(key);
		}
		return new ArrayList<String>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getListObject(String key) {
		if (containsKey(key)) {
			return (List<Object>)getObject(key);
		}
		return new ArrayList<Object>();
	}
	
	public Date getDate(String key) {
		Object rawDate = getObject(key);
		if (rawDate instanceof Long) {
			return new Date((Long) rawDate);
		} else if (rawDate instanceof String) {
			try {
				String rawString = (String) rawDate;
				if (rawString.matches("[0-9]{2}[\\/-][0-9]{2}[\\/-][0-9]{4}")) {
					return new SimpleDateFormat("dd/MM/yyyy").parse((String) rawString.replaceAll("-", "/"));
				} else if (rawString.matches("[0-9]{4}[\\/-][0-9]{2}[\\/-][0-9]{2}")) {
					return new SimpleDateFormat("yyyy/MM/dd").parse((String) rawString.replaceAll("-", "/"));
				} else {
					return null;
				}
			} catch (ParseException e) {
				return null;
			}
		} else {
			return (Date) rawDate;
		}
	}
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Boolean isPaged() {
		return paged;
	}

	public void setPaged(Boolean paged) {
		this.paged = paged;
	}

	public Map<String, Object> getCriterios() {
		return criterios;
	}
	
	public void setCriterios(Map<String, Object> criterios) {
		this.criterios = criterios;
	}

	public Map<String, String> getMapOrdenacao() {
		return mapOrdenacao;
	}

	public void setMapOrdenacao(Map<String, String> mapOrdenacao) {
		this.mapOrdenacao = mapOrdenacao;
	}
	
	public void addOrdenacao(String sort, String order) {
		if (StringUtils.isNotBlank(sort)) {
			getMapOrdenacao().put(sort, order);
		}
	}

	@JsonIgnore
	public Class<?> getPersistenceClass() {
		return persistenceClass;
	}

	public void setPersistenceClass(Class<?> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	@JsonIgnore
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public <E extends Object> E getAsClass(String chave, Class<E> clazz) throws ApplicationException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, criterios.get(chave));
			return mapper.readValue(sw.toString().getBytes(), clazz);
		} catch (JsonParseException e) {
			throw new ApplicationException(e, FWCode.FW0001.name());
		} catch (JsonMappingException e) {
			throw new ApplicationException(e, FWCode.FW0002.name());
		} catch (IOException e) {
			throw new ApplicationException(e, FWCode.FW0000.name());
		}
	}
}