package br.com.depro.mugetsu.carga.service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.08.2012
 */
public interface ImportacaoService {

    /**
     * Importa Media atras de arquivos XMLs
     * 
     * @param dir
     * @throws ApplicationException Caso algum erro ocorra
     */
    void importarFromXML(String dir) throws ApplicationException;
}
