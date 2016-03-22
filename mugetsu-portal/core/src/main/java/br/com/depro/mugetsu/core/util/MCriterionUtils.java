package br.com.depro.mugetsu.core.util;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.depro.fw.typezero.infrastructure.dao.CriterionUtils;
import br.com.depro.mugetsu.model.media.Media;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 20.11.2015
 */
public class MCriterionUtils extends CriterionUtils {

	public static void createFormatoMediaBlock(List<Criterion> criterions, MCriteria criteria, String formato) {
		if (criteria.containsKey(formato)) {
			criterions.add(Restrictions.eq(formato, criteria.getFormatoMedia(formato)));
		}
	}
	
	public static void createFormatoAnimeBlock(List<Criterion> criterions, MCriteria criteria, String formato) {
		if (criteria.containsKey(formato)) {
			criterions.add(Restrictions.eq(formato, criteria.getFormatoAnime(formato)));
		}
	}
	
	public static void createFormatoDoramaBlock(List<Criterion> criterions, MCriteria criteria, String formato) {
		if (criteria.containsKey(formato)) {
			criterions.add(Restrictions.eq(formato, criteria.getFormatoDorama(formato)));
		}
	}
	
	public static void createCriterionGenero(List<Criterion> criterions, MCriteria criteria, String generos) {
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
	
	public static void createCriterionNomeDefault(List<Criterion> criterions, MCriteria criteria, String campo) {
		if (!criteria.containsKey(campo)) {
			criterions.add(Restrictions.eq("titulo.principal", true));
		}
	}
}
