package br.com.depro.mugetsu.carga.principal.main;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
import br.com.depro.mugetsu.carga.anime.service.ImportarANNService;
import br.com.depro.mugetsu.carga.dorama.service.ImportarDWService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.08.2012
 */
public class ImportacaoGeral extends BaseTest {

    @TypezeroBean
    private ImportarANNService importaANNService;
    @TypezeroBean
    private ImportarDWService importarDramaWikiService;

    @Test
    public void basicTest() throws ApplicationException {
        int quantidadeInteracoes = 18000;
//        importaANNService.extrairHTML(quantidadeInteracoes);
//        importaANNService.extrairFormatosMediasANN(quantidadeInteracoes);
        importaANNService.importarFromCarga(quantidadeInteracoes);

//        importarDramaWikiService.extrairArquivoDeNomes();
//        importarDramaWikiService.extrairHTML();
//        importarDramaWikiService.importarFromCarga();
    }
}
