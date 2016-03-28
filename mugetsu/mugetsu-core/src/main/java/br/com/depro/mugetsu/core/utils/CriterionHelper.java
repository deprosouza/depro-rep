package br.com.depro.mugetsu.core.utils;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriterionUtils;
import br.com.depro.mugetsu.model.media.Media;

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

	public static void createCriterionGeneroBlock(List<Criterion> criterions, CriteriaHelper criteria, String generos) {
		if (criteria.containsKey(generos)) {
			for (Long id : criteria.getListLong(generos)) {
				String aliasMedia = "submedia" + id;
				String aliasGenero = "subgenero" + id;
				DetachedCriteria generoCriteria = DetachedCriteria.forClass(Media.class, aliasMedia);
				generoCriteria.createAlias("generos", aliasGenero);
				generoCriteria.add(Restrictions.eq(aliasGenero + ".id", id));
				generoCriteria.add(Property.forName(aliasMedia + ".id").eqProperty("media.id"));
				criterions.add(Subqueries.exists(generoCriteria.setProjection(Projections.property(aliasMedia + ".id"))));
			}
		}
	}
}
