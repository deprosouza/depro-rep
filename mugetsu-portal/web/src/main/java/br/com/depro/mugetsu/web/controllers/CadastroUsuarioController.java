package br.com.depro.mugetsu.web.controllers;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.service.security.LoginService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.zul.Window;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.09.2012
 */
public class CadastroUsuarioController extends BaseController {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = 2457353628062649025L;
    private Login login = new Login();
    @TypezeroBean
    private LoginService loginService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        super.init(view);
    }

    @Command
    public void efetuarCadastro(Event event) throws ApplicationException {
        this.validarEmailExistente();
        this.validarUsernameExistente();
        loginService.salvar(this.login);
    }

    /**
     * Valida email digitado
     */
    @Command
    public void validarEmailExistente() {
        String email = getLogin().getEmail();
        if (StringUtils.isNotBlank(email)) {
            if (!loginService.distinctEmail(email)) {
                throw new WrongValueException(Labels.getLabel("MSG.ERRO.EMAIL.EXISTENTE"));
            }
        }
    }

    /**
     * Valida username digitado
     */
    @Command
    public void validarUsernameExistente() {
        String username = getLogin().getUsername();
        if (StringUtils.isNotBlank(username)) {
            if (!loginService.distinctEmail(username)) {
                throw new WrongValueException(Labels.getLabel("MSG.ERRO.USERNAME.EXISTENTE"));
            }
        }
    }

    /**
     * Fecha modal de login
     */
    @Command
    public void fecharModalCadastro(@BindingParam("formCadastro") Window window) {
        window.detach();
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }
}
