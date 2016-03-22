package br.com.depro.mugetsu.web.vm;

import java.util.List;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.BeanManager;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.model.security.Role;
import br.com.depro.mugetsu.model.security.Session;
import br.com.depro.mugetsu.negocio.service.security.RoleService;
import br.com.depro.mugetsu.negocio.service.security.SessionService;
import br.com.depro.mugetsu.web.security.SessionWorkspace;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;

/**
 * @author rsouza
 * @version $Id$
 */
public abstract class BaseViewModel extends Window {

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
        return getWorkspace().contains(rights);
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
        return getWorkspace().isSessaoValida();
    }

    /**
     * Obtem credenciais do usuario logado
     *
     * @return Login
     */
    public Login getCredenciaisUsuario() {
        return getWorkspace().getSession().getLogin();
    }

    /**
     * Obtem a sessao do usuario logado
     *
     * @return Login
     */
    protected Session getSessionUsuario() {
        return getWorkspace().getSession();
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
        getWorkspace().doLoggout();
    }
    
    /**
     * Obtem sessao do usuario logado
     *
     * @return SessionWorkspace
     */
    private SessionWorkspace getWorkspace() {
        SessionWorkspace workspace = SessionWorkspace.getInstance();
        if (workspace == null) {
            SessionService service = (SessionService) MugetsuSpringUtils.getBean(SessionService.class);
            try {
				Session session = service.efetuarLogin("rsouza", "wqimkp", "127.0.0.1");
				workspace = new SessionWorkspace(session);
				SessionWorkspace.setAttribute(workspace);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            RoleService roleService = (RoleService) MugetsuSpringUtils.getBean(RoleService.class);
//            List<Role> roles = roleService.buscaRolesPorTipoAcesso(TipoAcessoEnum.COMMON);
//            workspace = new SessionWorkspace(roles.toArray(new Role[0]));
//            SessionWorkspace.setAttribute(workspace);
        }
        return workspace;
    }
}
