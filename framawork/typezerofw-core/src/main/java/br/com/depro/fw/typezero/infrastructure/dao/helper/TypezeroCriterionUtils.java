package br.com.depro.fw.typezero.infrastructure.dao.helper;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.depro.fw.typezero.infrastructure.utils.DateUtils;

/**
 * @author rsouza
 * @version 1.0 - versao inciao - 28.10.2015
 */
public class TypezeroCriterionUtils {

	public static void createCriterionLongBlock(List<Criterion> criterions, TypezeroCriteria criteria, String slong) {
		if (criteria.containsKey(slong)) {
			criterions.add(Restrictions.eq(slong, criteria.getLong(slong)));
		}
	}

	public static void createCriterionBooleanBlock(List<Criterion> criterions, TypezeroCriteria criteria, String object) {
		if (criteria.containsKey(object)) {
			criterions.add(Restrictions.eq(object, criteria.getObject(object)));
		}
	}

	public static void createCriterionStringBlock(List<Criterion> criterions, TypezeroCriteria criteria, String string, boolean isAccentOrCaseInsentive, MatchMode matchMode) {
		if (criteria.containsKey(string)) {
			criterions.add(Restrictions.like(string, criteria.getString(string, isAccentOrCaseInsentive), matchMode));
		}
	}

	public static void createCriterionPeriodoBlock(List<Criterion> criterions, TypezeroCriteria criteria, String incio, String fim) {
		if (criteria.containsKey(incio) && criteria.containsKey(fim)) {
			criterions.add(Restrictions.ge(incio, DateUtils.getDataComPrimeiroHorario(criteria.getDate(incio))));
			criterions.add(Restrictions.le(fim, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));

		} else if (criteria.containsKey(incio)) {
			criterions.add(Restrictions.ge(incio, DateUtils.getDataComPrimeiroHorario(criteria.getDate(incio))));

		} else if (criteria.containsKey(fim)) {
			criterions.add(Restrictions.le(fim, DateUtils.getDataComUltimoHorario(criteria.getDate(fim))));
		} else if (criteria.containsKey(fim + "-isnull")) {
			criterions.add(Restrictions.isNull(fim));
		}
	}
}
