package br.com.depro.mugetsu.core.media.service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 20.03.2016
 */
public interface MediaService extends TypezeroGenericService<Media> {

	/**
	 * @param media
	 * @return
	 */
	boolean isMediaExistente(Media media);
}
