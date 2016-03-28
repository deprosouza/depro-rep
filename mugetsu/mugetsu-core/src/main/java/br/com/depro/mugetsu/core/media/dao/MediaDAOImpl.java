package br.com.depro.mugetsu.core.media.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.mugetsu.core.utils.CriteriaHelper;
import br.com.depro.mugetsu.core.utils.CriterionHelper;
import br.com.depro.mugetsu.model.media.Media;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 20.03.2016
 */
@Repository
public class MediaDAOImpl extends TypezeroGenericJPADAO<Media> implements MediaDAO {

	@Override
	public List<Media> findByAttrs(TypezeroCriteria criteriaRaw) {
		CriteriaHelper criteria = (CriteriaHelper) criteriaRaw;
		List<Criterion> criterions = new ArrayList<Criterion>();

		Map<String, String> alias = new HashMap<String, String>();
		alias.put("nomes", "alternativeName");

		CriterionHelper.createCriterionStringBlock(criterions, criteria, "alternativeName.nome", true, MatchMode.EXACT);
		CriterionHelper.createCriterionFormatoMediaBlock(criterions, criteria, "formatoMedia");
		CriterionHelper.createCriterionFormatoAnimeBlock(criterions, criteria, "formatoAnime");
		CriterionHelper.createCriterionFormatoDoramaBlock(criterions, criteria, "formatoDorama");

		return executeCriteria(criteria, criterions, null, alias, null, Criteria.DISTINCT_ROOT_ENTITY);
	}

	public Media findIfMediaExists(CriteriaHelper criteria) {
		List<Media> medias = findByAttrs(criteria);
		if (CollectionUtils.isNotEmpty(medias)) {
			return medias.get(0);
		}
		return null;
	}
}
