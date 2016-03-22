package br.com.depro.mugetsu.web.vm.perfil;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.security.Session;
import br.com.depro.mugetsu.negocio.service.security.SessionService;
import br.com.depro.mugetsu.web.security.SessionWorkspace;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 16/08/2013
 */
public class AcessoVM extends BaseViewModel {

    /** Numero serial da classe */
	private static final long serialVersionUID = 2876366792622100773L;
	@TypezeroBean
    private SessionService sessionService;
    private String usernameOrEmail;
    private String password;

    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    @Override
    protected void doAlways() throws ApplicationException {
    }

    @Override
    protected void doWithSession() throws ApplicationException {
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     * Atualiza a sessao
     * @param session
     */
    @Command
    public void finalizarProcessoLogin() {
        try {
            Session session = sessionService.efetuarLogin(getUsernameOrEmail(), getPassword(), Executions.getCurrent().getRemoteAddr());
            SessionWorkspace workspace = new SessionWorkspace(session);
            SessionWorkspace.setAttribute(workspace);
            Executions.sendRedirect("/");
        } catch (ApplicationException nexp) {
            showMessageErro(nexp);
        }
    }
    
    /**
     * Fecha modal de login
     */
    @Command
    public void fecharModalLogin(@BindingParam("formLogin") Window window) {
        window.detach();
    }
    
    /**
     * @return the usernameOrEmail
     */
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    /**
     * @param usernameOrEmail the usernameOrEmail to set
     */
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
