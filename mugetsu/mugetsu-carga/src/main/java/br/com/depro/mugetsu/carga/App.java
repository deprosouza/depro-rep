package br.com.depro.mugetsu.carga;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
import br.com.depro.mugetsu.carga.service.ann.ImportarANNService;

/**
 * Hello world!
 *
 */
public class App extends BaseTest {

	@TypezeroBean
	private ImportarANNService service;

	@Test
	public void basicTest() throws ApplicationException {
		int quantidadeInteracoes = 4885;
//		service.extrairHTML(quantidadeInteracoes);
//		service.extrairFormatosMediasANN(quantidadeInteracoes);
		service.importarFromCarga(quantidadeInteracoes);
	}
}
