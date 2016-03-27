package br.com.depro.mugetsu.core.media.service;

import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.media.dao.MediaDAO;
import br.com.depro.mugetsu.core.utils.CriteriaHelper;
import br.com.depro.mugetsu.model.media.Media;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 20.03.2016
 */
@Service
public class MediaServiceImpl extends TypezeroGenericServiceImpl<Media, MediaDAO> implements MediaService {

	public boolean isMediaExistente(Media media) {
		CriteriaHelper criteria = new CriteriaHelper();
		criteria.addCriterio("formatoMedia", media.getFormatoMedia());
		criteria.addCriterio("formatoAnime", media.getFormatoAnime());
		criteria.addCriterio("formatoDorama", media.getFormatoDorama());
		
		criteria.addCriterio("alternativeName.nome", media.getNomePrincipal());
		
		return getDAO().findIfMediaExists(criteria) != null ? true : false;
	}

}
