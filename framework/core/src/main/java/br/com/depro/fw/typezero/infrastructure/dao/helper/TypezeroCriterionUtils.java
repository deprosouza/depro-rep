package br.com.depro.fw.typezero.infrastructure.dao.helper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.depro.fw.typezero.infrastructure.bean.utils.DateUtils;

/**
 * @author rsouza
 * @version 1.0 - versao inciao - 28.10.2015
 */
public class TypezeroCriterionUtils {

	public static final String IN = "-in";
	public static final String NOT_IN = "-notin";
	
	public static final String IS_NULL = "-isnull";
	public static final String NOT_NULL = "-notnull";
	
	public static void createLongBlock(List<Criterion> criterions, TypezeroCriteria criteria, String slong) {
		if (criteria.containsKey(slong)) {
			criterions.add(Restrictions.eq(slong, criteria.getLong(slong)));
		}
	}
	
	public static void createIntBlock(List<Criterion> criterions, TypezeroCriteria criteria, String sInt) {
		if (criteria.containsKey(sInt)) {
			criterions.add(Restrictions.eq(sInt, criteria.getInt(sInt)));
		}
	}
	
	public static void createIsNotNullBlock(List<Criterion> criterions, TypezeroCriteria criteria, String string) {
		if (criteria.containsKey(string + NOT_NULL)) {
			criterions.add(Restrictions.isNotNull(string));
		}
	}
	
	public static void createIsNullOrNotNullBlock(List<Criterion> criterions, TypezeroCriteria criteria, String string) {
		if (criteria.containsKey(string + IS_NULL)) {
			criterions.add(Restrictions.isNull(string));
		}
		
		if (criteria.containsKey(string + NOT_NULL)) {
			criterions.add(Restrictions.isNotNull(string));
		}
	}

	public static void createBooleanBlock(List<Criterion> criterions, TypezeroCriteria criteria, String object) {
		if (criteria.containsKey(object)) {
			criterions.add(Restrictions.eq(object, criteria.getObject(object)));
		}
	}

	public static void createStringBlock(List<Criterion> criterions, TypezeroCriteria criteria, String key, boolean isAccentOrCaseInsentive, MatchMode matchMode) {
		if (criteria.containsKey(key)) {
			criterions.add(Restrictions.like(key, criteria.getString(key, isAccentOrCaseInsentive), matchMode));
		}
	}
	
	public static void createStringOrNullBlock(List<Criterion> criterions, TypezeroCriteria criteria, String key, boolean isAccentOrCaseInsentive, boolean isNull, MatchMode matchMode) {
		if (criteria.containsKey(key)) {
			criterions.add(Restrictions.or(Restrictions.like(key, criteria.getString(key, isAccentOrCaseInsentive), matchMode), isNull ? Restrictions.isNull(key) : Restrictions.isNotNull(key)));
		}
	}
	
	public static void createFieldStringOrBlock(List<Criterion> criterions, TypezeroCriteria criteria, String key, boolean isAccentOrCaseInsentive, MatchMode matchMode, String... fields) {
		if (criteria.containsKey(key)) {
			List<Criterion> criterioList = new ArrayList<Criterion>(); 
			for (String field : fields) {
				criterioList.add(Restrictions.like(field, criteria.getString(key, isAccentOrCaseInsentive), matchMode));
			}
			criterions.add(Restrictions.or(criterioList.toArray(new Criterion[]{})));
		}
	}
	
	public static void createBigDecimalBlock(List<Criterion> criterions, TypezeroCriteria criteria, String string) {
		if (criteria.containsKey(string)) {
			criterions.add(Restrictions.eq(string, criteria.getBigDecimal(string)));
		}
	}

	public static void createPeriodoByFieldsBlock(List<Criterion> criterions, TypezeroCriteria criteria, String incio, String fim) {
		if (criteria.containsKey(incio) && criteria.containsKey(fim)) {
			criterions.add(Restrictions.ge(incio, DateUtils.getDataComPrimeiroHorario(criteria.getDate(incio))));
			criterions.add(Restrictions.le(fim, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));

		} else if (criteria.containsKey(incio)) {
			criterions.add(Restrictions.ge(incio, DateUtils.getDataComUltimoHorario(criteria.getDate(incio))));

		} else if (criteria.containsKey(fim)) {
			criterions.add(Restrictions.le(fim, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));
		} else if (criteria.containsKey(fim + IS_NULL)) {
			criterions.add(Restrictions.isNull(fim));
		}
	}
	
	public static void createPeriodoByFieldsBlock(List<Criterion> criterions, TypezeroCriteria criteria, String fieldInicio, String fieldFim, String sdate, Boolean isNull) {
		if (criteria.containsKey(sdate)) {
			criterions.add(Restrictions.le(fieldInicio, DateUtils.getDataComPrimeiroHorario(criteria.getDate(sdate))));
			if (isNull != null) {
				criterions.add(Restrictions.or(Restrictions.ge(fieldFim, DateUtils.getDataComUltimoHorario(criteria.getDate(sdate))), isNull ? Restrictions.isNull(fieldFim) : Restrictions.isNotNull(fieldFim)));
			} else {
				criterions.add(Restrictions.ge(fieldFim, DateUtils.getDataComUltimoHorario(criteria.getDate(sdate))));
			}
		}
	}
	
	public static void createPeriodoByDateBlock(List<Criterion> criterions, TypezeroCriteria criteria, String incio, String fim, String field) {
		if (criteria.containsKey(incio) && criteria.containsKey(fim)) {
			criterions.add(Restrictions.ge(field, DateUtils.getDataComPrimeiroHorario(criteria.getDate(incio))));
			criterions.add(Restrictions.le(field, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));

		} else if (criteria.containsKey(incio)) {
			criterions.add(Restrictions.ge(field, DateUtils.getDataComPrimeiroHorario(criteria.getDate(incio))));

		} else if (criteria.containsKey(fim)) {
			criterions.add(Restrictions.le(field, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));
		} else if (criteria.containsKey(fim + IS_NULL)) {
			criterions.add(Restrictions.isNull(field));
		}
	}
	
	public static void createInOrNotInBlock(List<Criterion> criterions, TypezeroCriteria criteria, String string) {
		if (criteria.containsKey(string + IN)) {
			criterions.add(Restrictions.in(string, criteria.getListObject(string)));
		}
		if (criteria.containsKey(string + NOT_IN)) {
			criterions.add(Restrictions.not(Restrictions.in(string, criteria.getListObject(string))));
		}
		
	}

	
	public static void createEqPropertiesBlock(DetachedCriteria dc, String propertyDC, String propertyMain) {
		dc.add(Restrictions.eqProperty(propertyDC, propertyMain));
	}
	
	public static void createEqPropertiesBlock(List<Criterion> criterions, String propertyDC, String propertyMain) {
		criterions.add(Restrictions.eqProperty(propertyDC, propertyMain));
	}
}
