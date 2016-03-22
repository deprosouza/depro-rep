package br.com.depro.mugetsu.core.service.media;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.core.util.MCriteria;
import br.com.depro.mugetsu.model.media.Media;

/**
 * Interface de serviços de media <br><br>
 *
 * @author rsouza
 * @version 1.0 - Versão Inicial - 29.06.2012
 */
public interface MediaService extends TypezeroGenericService<Media> {

	/**
	 * @param mcriteria
	 * @return
	 */
	public List<Media> buscaPorAtributos(MCriteria mcriteria);
	
    /**
     * insere medias em lote.
     *
     * @param medias
     * @throws ApplicationException Caso algum erro ocorra
     */
    void inserirLote(List<Media> medias) throws ApplicationException;

    /**
     * Adicionar conteudo a media especificada
     *
     * @param id
     * @param quantidade
     * @throws ApplicationException Caso algum erro ocorra.
     */
    void adicionarConteudoPorMediaId(Long id, Integer quantidade) throws ApplicationException;

    /**
     * Adicionar conteudo a media especificada
     *
     * @param media
     * @param quantidade
     * @throws ApplicationException Caso algum erro ocorra.
     */
    void inserirConteudoPorMedia(Media media, Integer quantidade) throws ApplicationException;
}
