package br.com.depro.mugetsu.negocio.service.media;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.MediaTitulo;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versão Incial - 30.06.2012
 */
public interface MediaTituloService extends TypezeroGenericService<MediaTitulo> {

    /**
     * Inseri uma {@link MediaTitulo} nova
     * 
     * @param media
     * @return TRUE caso titulos estajam disponiveis
     * @throws ApplicationException Caso algum erro ocorra.
     */
    boolean validarMediaTitulos(Media media) throws ApplicationException;
}
