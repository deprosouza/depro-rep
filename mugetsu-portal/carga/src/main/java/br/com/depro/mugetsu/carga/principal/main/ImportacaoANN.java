package br.com.depro.mugetsu.carga.principal.main;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
import br.com.depro.mugetsu.carga.anime.service.ImportarANNService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 04.08.2012
 */
public class ImportacaoANN extends BaseTest {

    @TypezeroBean
    private ImportarANNService service;

    @Test
    public void basicTest() throws ApplicationException {
        int quantidadeInteracoes = 17010;
//        service.extrairHTML(quantidadeInteracoes);
//        service.extrairFormatosMediasANN(quantidadeInteracoes);
        service.importarFromCarga(quantidadeInteracoes);
    }
    
}
