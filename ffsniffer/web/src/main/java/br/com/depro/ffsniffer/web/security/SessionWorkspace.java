package br.com.depro.ffsniffer.web.security;

import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.Executions;

import br.com.depro.ffsniffer.model.seguranca.Permissao;
import br.com.depro.ffsniffer.model.seguranca.Role;
import br.com.depro.ffsniffer.model.seguranca.Session;

/**
 * @author rsouza
 * @version 1.0 - Data: 10.03.2015
 */
public class SessionWorkspace {

    private Session session;
    private String userLanguage;
    private String browserType;
    private Set<String> grantedAuthoritySet = null;

    /**
     * Construtor padrao da classe
     */
    public SessionWorkspace(Role... roles) {
        this.grantedAuthoritySet = new HashSet<String>();
        for (Role role : roles) {
            for (Permissao permissao : role.getPermissoes()) {
                this.grantedAuthoritySet.add(permissao.getChave());
            }
        }
        this.browserType = Executions.getCurrent().getUserAgent();
    }

    /**
     * Construtor da classe
     *
     * @param session
     */
    public SessionWorkspace(Session session) {
        this.session = session;
        this.grantedAuthoritySet = new HashSet<String>();
        for (Role role : this.session.getLogin().getRoles()) {
            for (Permissao permissao : role.getPermissoes()) {
                this.grantedAuthoritySet.add(permissao.getChave());
            }
        }
        this.browserType = Executions.getCurrent().getUserAgent();
    }

    /**
     * Valida se usuario tem permissao especificada
     *
     * @param right
     * @return TRUE caso tenha
     */
    public boolean contains(String right) {
        return grantedAuthoritySet.contains(right);
    }

    /**
     *
     * @return
     */
    public boolean isSessaoValida() {
        boolean retorno = false;
        if (this.session != null) {
            retorno = true;
        }
        return retorno;
    }

    /**
     * Encerra a sessao do usuario.
     */
    public void doLoggout() {
        setSession(null);
        Executions.getCurrent().getSession().removeAttribute("usersession");
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * @return the userLanguage
     */
    public String getUserLanguage() {
        return userLanguage;
    }

    /**
     * @param userLanguage the userLanguage to set
     */
    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    /**
     * @return the browserType
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * @param browserType the browserType to set
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    /**
     * @return the grantedAuthoritySet
     */
    public Set<String> getGrantedAuthoritySet() {
        return grantedAuthoritySet;
    }

    /**
     * @param grantedAuthoritySet the grantedAuthoritySet to set
     */
    public void setGrantedAuthoritySet(Set<String> grantedAuthoritySet) {
        this.grantedAuthoritySet = grantedAuthoritySet;
    }

    /**
     * Verifica se a permissao esta concedida para o usuario logado <br>
     *
     * @param nomePermissao
     * @return true, se ele tiver permissao false, se ele nao tiver
     * permissao.<br>
     */
    public boolean isAllowed(String nomePermissao) {
        return getGrantedAuthoritySet().contains(nomePermissao);
    }

    /**
     * Retorna o workspace do usuario com todas as informacoes, que esta na
     * sessao. <br>
     *
     * @return SessionWorkspace
     */
    public static SessionWorkspace getInstance() {
        return (SessionWorkspace) (Executions.getCurrent().getSession().getAttribute("usersession"));
    }

    /**
     *
     * @param SessionWorkspace
     * @return
     */
    public static SessionWorkspace setAttribute(SessionWorkspace SessionWorkspace) {
        return (SessionWorkspace) (Executions.getCurrent().getSession().setAttribute("usersession", SessionWorkspace));
    }
}
