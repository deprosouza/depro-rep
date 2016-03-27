package br.com.depro.mugetsu.core.utils;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriterionUtils;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 26.03.2016
 */
public class CriterionHelper extends TypezeroCriterionUtils {

	public static void createCriterionFormatoMediaBlock(List<Criterion> criterions, CriteriaHelper criteria, String name) {
		if (criteria.containsKey(name)) {
			criterions.add(Restrictions.eq(name, criteria.getFormatoMedia(name)));
		}
	}
	
	public static void createCriterionFormatoAnimeBlock(List<Criterion> criterions, CriteriaHelper criteria, String name) {
		if (criteria.containsKey(name)) {
			criterions.add(Restrictions.eq(name, criteria.getFormatoAnime(name)));
		}
	}
	
	public static void createCriterionFormatoDoramaBlock(List<Criterion> criterions, CriteriaHelper criteria, String name) {
		if (criteria.containsKey(name)) {
			criterions.add(Restrictions.eq(name, criteria.getFormatoDorama(name)));
		}
	}
}
