package br.com.depro.fw.typezero.infrastructure.dao.helper;

import java.io.Serializable;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.depro.fw.typezero.infrastructure.model.NoEntidadeBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypezeroCriteria extends NoEntidadeBase implements Serializable {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = 8519130949335324669L;
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";
	private Integer limit = -1;
	private Integer offset = -1;
	private Boolean paged = Boolean.FALSE;
	private Map<String, String> mapOrdenacao = new HashMap<String, String>();
	public Map<String, Object> criterios = new HashMap<String, Object>();

	public TypezeroCriteria() {
		super();
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
	
	public Long getLong(String key) {
	    if (StringUtils.isNotBlank(getString(key))) {
		    return Long.valueOf(getString(key));
	    }
	    return null;
	}
	
	public Boolean getBoolean(String key) {
		return Boolean.valueOf(getString(key));
	}
	
	public void addCriterio(String chave, Object valor) {
		criterios.put(chave, valor);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListString(String key) {
		if (containsKey(key)) {
			return (List<String>)getObject(key);
		}
		return new ArrayList<String>();
	}
	
	public Date getDate(String key) {
		Object rawDate = getObject(key);
		if (rawDate instanceof Long) {
			return new Date((Long) rawDate);
		} else if (rawDate instanceof String) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse((String) rawDate);
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

}