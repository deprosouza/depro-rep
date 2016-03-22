package br.com.depro.mugetsu.web.controllers;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Div;

import br.com.depro.fw.typezero.infrastructure.spring.BeanManager;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.07.2012
 */
public class BaseController extends Div {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = -3047434075823239622L;
    private PropConfig propConfig;

    /**
     * Inicializa componentes
     *
     * @param view
     */
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        BeanManager.injectDSBean(this);
    }
    
    /**
     * Obtem arquivo de configuracoes da aplicacao
     * @return 
     */
    public PropConfig getPropConfig() {
        if (propConfig == null) {
            this.propConfig = (PropConfig) MugetsuSpringUtils.getBean(PropConfig.class);
        }
        return propConfig;
    }
}
