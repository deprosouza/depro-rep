package br.com.depro.mugetsu.core.dao.media;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.mugetsu.core.util.MCriteria;
import br.com.depro.mugetsu.core.util.MCriterionUtils;
import br.com.depro.mugetsu.model.media.Media;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Incial - 29.06.2012
 */
@Service
public class MediaDAO extends TypezeroGenericJPADAO<Media> {

	public List<Media> findByAttrs(MCriteria mcriteria) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Map<String, String> alias = new HashMap<String, String>();
		Disjunction disjunction = Restrictions.disjunction();
		ProjectionList projections = Projections.projectionList();
		
		alias.put("titulos", "titulo");
		
		projections.add(Projections.property("id").as("id"));
		projections.add(Projections.property("titulo.nome").as("nomePrincipal"));
		projections.add(Projections.property("episodios").as("episodios"));
		projections.add(Projections.property("volumes").as("volumes"));
		projections.add(Projections.property("pathImagem").as("pathImagem"));
		projections.add(Projections.property("formatoMedia").as("formatoMedia"));
		projections.add(Projections.property("formatoAnime").as("formatoAnime"));
		projections.add(Projections.property("formatoDorama").as("formatoDorama"));
		
		MCriterionUtils.createCriterionLongBlock(criterions, mcriteria, "id");
		MCriterionUtils.createFormatoMediaBlock(criterions, mcriteria, "formatoMedia");
		MCriterionUtils.createFormatoAnimeBlock(criterions, mcriteria, "formatoAnime");
		MCriterionUtils.createFormatoDoramaBlock(criterions, mcriteria, "formatoDorama");
		MCriterionUtils.createCriterionStringBlock(criterions, mcriteria, "titulo.nome", false, MatchMode.ANYWHERE);
		MCriterionUtils.createCriterionStringBlock(criterions, mcriteria, "pathImagem", false, MatchMode.START);
		MCriterionUtils.createCriterionGenero(criterions, mcriteria, "genero.id");
		MCriterionUtils.createCriterionNomeDefault(criterions, mcriteria, "titulo.nome");
		
		disjunction = Restrictions.disjunction();
		for (String letra : mcriteria.getListString("letras")) {
			disjunction.add(Restrictions.like("titulo.nome", letra, MatchMode.START));
		}
		
		return  executeCriteria(mcriteria, criterions, projections, alias, disjunction) ;
	}

}
