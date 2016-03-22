package br.com.depro.mugetsu.carga.principal.main;

import java.text.ParseException;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
import br.com.depro.mugetsu.carga.dorama.service.ImportarDWService;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10/02/2013
 */
public class ImportacaoDW extends BaseTest {

	@TypezeroBean
	private ImportarDWService service;

	@Test
	public void extrairConteudo() throws ApplicationException, CoreException,
			ParseException {
		 service.extrairArquivoDeNomes();
		 service.extrairHTML();
		 service.importarFromCarga();
	}
}