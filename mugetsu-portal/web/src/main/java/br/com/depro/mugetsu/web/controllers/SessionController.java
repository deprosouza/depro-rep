package br.com.depro.mugetsu.web.controllers;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.service.security.SessionService;
import br.com.depro.mugetsu.web.security.SessionWorkspace;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 09.09.2012
 */
public class SessionController extends BaseController {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = -1002111611176188807L;
    protected static SessionWorkspace workspace;
    @TypezeroBean
    protected SessionService sessionService;
    private String userNameOrEmail;
    private String userPwd;

    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        super.init(view);
    }

    /**
     *
     * @return
     */
    public static boolean isSessionValid() {
        return validaSessao();
    }

    /**
     * Metodo que retorno usuario da sessao
     *
     * @return
     */
    public static Login obterUsuarioSessao() {
        workspace = null;
        Login login = null;
        if (validaSessao()) {
            login = workspace.getSession().getLogin();
        }
        return login;
    }

    /**
     *
     *
     * @param permissao
     * @return
     */
    public static boolean isAllowed(String permissao) {
        workspace = null;
        boolean retorno = false;
        if (validaSessao()) {
            retorno = workspace.isAllowed(permissao);
        }
        return retorno;
    }

    /**
     * ]
     *
     *
     * @param workspace
     * @return
     */
    private static boolean validaSessao() {
        workspace = SessionWorkspace.getInstance();
        boolean retorno = false;
        if (workspace != null) {
            retorno = workspace.isSessaoValida();
        }
        return retorno;
    }

    /**
     * @return
     */
    public SessionWorkspace getWorkspace() {
        return workspace;
    }

    /**
     *
     * @return
     */
    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    /**
     *
     * @param userNameOrEmail
     */
    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }

    /**
     *
     * @return
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     *
     * @param userPwd
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
