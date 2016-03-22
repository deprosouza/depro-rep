package br.com.depro.mugetsu.core.service.media;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.nome.MediaNome;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versão Incial - 30.06.2012
 */
public interface MediaTituloService extends TypezeroGenericService<MediaNome> {

    /**
     * Inseri uma {@link MediaNome} nova
     * 
     * @param media
     * @return TRUE caso titulos estajam disponiveis
     * @throws ApplicationException Caso algum erro ocorra.
     */
    boolean validarMediaTitulos(Media media) throws ApplicationException;
}
