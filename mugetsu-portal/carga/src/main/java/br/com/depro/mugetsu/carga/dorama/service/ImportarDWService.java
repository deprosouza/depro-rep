package br.com.depro.mugetsu.carga.dorama.service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10/02/2013
 */
public interface ImportarDWService extends TypezeroGenericService<Media> {

	/**
	 * Atualiza dados da media
	 * @param media
	 * @return Entidade {@link Media} atualizada
	 * @throws ApplicationException
	 */
	Media atualizarDadosMedia(Media media) throws ApplicationException;
	
    /**
     * Extrai da web nomes conteudo
     * @throws ApplicationException TODO
     */
    void extrairArquivoDeNomes() throws ApplicationException;

    /**
     * Extrai da web conteudo para insercao
     * @throws ApplicationException TODO
     */
    void extrairHTML() throws ApplicationException;

    /**
     * Analiza e persiste conteudo no banco
     * @throws ApplicationException TODO
     */
    void importarFromCarga() throws ApplicationException;
}
