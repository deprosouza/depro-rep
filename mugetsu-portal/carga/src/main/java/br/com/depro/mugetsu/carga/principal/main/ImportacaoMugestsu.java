package br.com.depro.mugetsu.carga.principal.main;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.carga.dmu.service.ImportarDMUService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.08.2012
 */
public class ImportacaoMugestsu extends BaseTest {

    @TypezeroBean
    private ImportarDMUService importacaoService;
    @TypezeroBean
    private PropConfig propConfig;

    /**
     * @see BaseTest#basicTest()
     */
    @Test
    public void basicTest() throws ApplicationException {
        importacaoService.importarFromXML(propConfig.get("dir.path.output.carga.xml"));
    }
}
