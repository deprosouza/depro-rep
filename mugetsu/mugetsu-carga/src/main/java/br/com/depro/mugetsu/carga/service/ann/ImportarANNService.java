package br.com.depro.mugetsu.carga.service.ann;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Media;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 06.08.2012
 */
public interface ImportarANNService {

	/**
	 * Metodo chamado em tela para atualizar dados da media manualmente
	 * 
	 * @param media
	 * @return Entidade {@link Media} atualizada
	 * @throws ApplicationException caso algum erro ocorra
	 */
	Media atualizarDadosMedia(Media media) throws ApplicationException;
	
    /**
     * Baixa conteudo de medias ANN
     * @param quantidadeInteracao TODO
     * 
     * @throws ApplicationException Caso algum erro ocorra.
     */
    void extrairHTML(int quantidadeInteracao) throws ApplicationException;

    /**
     * 
     * 
     * @param quantidadeInteracao
     */
    void importarFromCarga(int quantidadeInteracao) throws ApplicationException;

    /**
     * 
     * @param quantidadeInterecoes
     */
    void extrairFormatosMediasANN(int quantidadeInterecoes);
}
