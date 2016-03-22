package br.com.depro.mugetsu.negocio.service.media;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 * Interface de serviços de media <br><br>
 *
 * @author rsouza
 * @version 1.0 - Versão Inicial - 29.06.2012
 */
public interface MediaService extends TypezeroGenericService<Media> {

    /**
     * insere medias em lote.
     *
     * @param medias
     * @throws ApplicationException Caso algum erro ocorra
     */
    void inserirLote(List<Media> medias) throws ApplicationException;

    /**
     * Obtem media pelo nome da imgagem de exbicao
     *
     * @param nomeImagem
     * @return Entidade Media
     * @throws ApplicationException Caso algum erro ocorra
     */
    Media obterMediaPorNomeImagem(String nomeImagem) throws ApplicationException;

    /**
     * Lista entidades de media por primeira letra e formato da media
     *
     * @param letra
     * @param isConsultaImagem
     * @param formatoMedia
     * @return Lista de entidade de {@link Media}
     * @throws ApplicationException Caso algum erro ocorra
     */
    List<Media> listarMediasPorPrimeiraLetraEFormatoMedia(String letra, boolean isConsultaImagem, FormatoMedia formatoMedia)
            throws ApplicationException;

    /**
     * Lista entidades de media por primeira letra
     *
     * @param letra
     * @param isConsultaImagem 
     * @param formatoMedia
     * @return Lista de entidade de {@link Media}
     * @throws ApplicationException Caso algum erro ocorra
     */
    List<Media> listarMediasPorPrimeiraLetra(String letra, boolean isConsultaImagem) throws ApplicationException;

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
