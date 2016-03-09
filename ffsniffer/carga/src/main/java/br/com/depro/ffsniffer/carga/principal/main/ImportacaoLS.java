package br.com.depro.ffsniffer.carga.principal.main;

import org.junit.Test;

import br.com.depro.ffsniffer.carga.linkshell.service.ImportarLSService;
import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 04.08.2012
 */
public class ImportacaoLS extends BaseTest {

    @TypezeroBean
    private static ImportarLSService service;

    @Test
    public void basicTest() throws ApplicationException {
//        super.testCrudSimples("br.com.depro.ffsniffer.");
    	service.importarLinkshells(true, false);
    }
}
