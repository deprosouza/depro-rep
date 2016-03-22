package br.com.depro.mugetsu.core.service.media;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Episodio;
import br.com.depro.mugetsu.model.media.Media;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.07.2012
 */
public interface ConteudoService extends TypezeroGenericService<Episodio> {

    /**
     * Cria conteudo da media no processo de cadastramento da media
     *
     * @param media
     * @throws ApplicationException Caso algum erro ocorra
     */
    void inserirConteudoMedia(Media media) throws ApplicationException;

    /**
     * Lista conteudos pelo id da media
     *
     * @param id
     * @return Lista de entidades
     * @throws ApplicationException Caso algum erro ocorra.
     */
    List<Episodio> listarConteudoPorMediaId(Long id) throws ApplicationException;

    /**
     * Lista conteudos pela media
     *
     * @param media
     * @return Lista de entidades
     * @throws ApplicationException Caso algum erro ocorra.
     */
    List<Episodio> listarConteudoPorMedia(Media media) throws ApplicationException;
}
