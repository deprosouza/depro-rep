package br.com.depro.ffsniffer.web.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Div;

import br.com.depro.ffsniffer.core.service.seguranca.RoleService;
import br.com.depro.ffsniffer.model.seguranca.Login;
import br.com.depro.ffsniffer.model.seguranca.Role;
import br.com.depro.ffsniffer.model.seguranca.Session;
import br.com.depro.ffsniffer.web.security.SessionWorkspace;
import br.com.depro.ffsniffer.web.util.SpringContextWebUtils;
import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.BeanManager;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public abstract class BaseViewModel extends Div {

    /** Numero de serie da classe */
    private static final long serialVersionUID = 5950254896301922884L;
    @TypezeroBean
    private PropConfig propConfig;

    /**
     * metodo executado sempre para qualquer cenario
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    protected abstract void doAlways() throws ApplicationException;

    /**
     * Metodo que configura visualizacao se usuario estiver logado
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    protected abstract void doWithSession() throws ApplicationException;

    /**
     * Metodo que configura visualizacao se usuario estiver logado
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    protected abstract void doWithoutSession() throws ApplicationException;

    /**
     * Inicializa view model
     *
     * @param view
     */
    @Init
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        try {
            Selectors.wireComponents(view, this, false);
            Selectors.wireEventListeners(this, this);
            BeanManager.injectDSBean(this);
            doConfiguration();
        } catch (ApplicationException nexp) {
            showMessageErro(nexp);
        }
    }

    /**
     * Faz configuracao da visualizacao
     */
    protected void doConfiguration() throws ApplicationException {
        doAlways();
        if (isSessaoValida()) {
            doWithSession();
        } else {
            doWithoutSession();
        }
    }

    /**
     * 
     * @param rightses
     * @return 
     */
    public boolean hasPermissao(String rights) {
        return configureWorkspace().contains(rights);
    }

    /**
     * Trata qualquer excecao que possa ocorrer
     *
     * @param erro
     */
    public void showMessageErro(Throwable erro) {
        String messagem = erro.getMessage();
        String key = "\\" + messagem.substring(0, messagem.indexOf("]")) + "\\]";
        String erromsg = Labels.getLabel(key.substring(2, key.indexOf("]") - 1));
        String retorno = messagem.replaceAll(key, erromsg);
        String titulo = Labels.getLabel("label.erro.negocio");
        Messagebox.show(retorno, titulo, Messagebox.OK, Messagebox.EXCLAMATION);
    }

    /**
     * Indica se sessao do usuario e valida
     *
     * @return
     */
    public boolean isSessaoValida() {
        return configureWorkspace().isSessaoValida();
    }

    /**
     * Obtem credenciais do usuario logado
     *
     * @return Login
     */
    public Login getCredenciaisUsuario() {
        return configureWorkspace().getSession().getLogin();
    }

    /**
     * Obtem a sessao do usuario logado
     *
     * @return Login
     */
    protected Session getSessionUsuario() {
        return configureWorkspace().getSession();
    }

    /**
     * @return the propConfig
     */
    public PropConfig getPropConfig() {
        return propConfig;
    }

    /**
     * Realiza o logout da aplicacao
     */
    public void doLoggout() {
        configureWorkspace().doLoggout();
    }

    /**
     * Obtem sessao do usuario logado
     *
     * @return SessionWorkspace
     */
    public static SessionWorkspace configureWorkspace() {
        SessionWorkspace workspace = SessionWorkspace.getInstance();
        if (workspace == null) {
//              SessionService service = (SessionService) GekkoStateSpringUtils.getBean(SessionService.class);
//		Session session = service.efetuarLogin("rsouza", "wqimkp", "127.0.0.1");
            RoleService roleService = (RoleService) SpringContextWebUtils.getBean(RoleService.class);
            List<Role> roles = roleService.buscaRolesPorNivel(0);
            workspace = new SessionWorkspace(roles.toArray(new Role[0]));
            SessionWorkspace.setAttribute(workspace);
        }
        return workspace;
    }
}
