package br.com.depro.mugetsu.carga.dmu.service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.media.Media;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.08.2012
 */
public interface ImportarDMUService {

	/**
	 * Atualiza dados da media
	 * @param id
	 * @return Entidade {@link Media} atualizada
	 * @throws ApplicationException
	 */
	Media atualizarDadosMedia(Long id) throws ApplicationException;
	
    /**
     * Importa Media atras de arquivos XMLs
     * 
     * @param dir
     * @throws ApplicationException Caso algum erro ocorra
     */
    void importarFromXML(String dir) throws ApplicationException;
}
