package br.com.depro.ffsniffer.carga.linkshell.service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;



/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.02.2015
 */
public interface ImportarLSService {

	void importarLinkshells(boolean isFromArquivo, boolean onlyNewOnes) throws ApplicationException;
}
