package br.com.depro.mugetsu.web.utils;

import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.web.security.SessionWorkspace;

/**
 *
 * @author rsouza
 */
public class SessionUtils {

    /**
     *
     */
    public static Login getCredenciais() {
        return getWorkspace().getSession().getLogin();
    }

    /**
     *
     */
    public static boolean hasPermissao(String rights) {
        return getWorkspace().contains(rights);
    }

    /**
     *
     * @return
     */
    private static SessionWorkspace getWorkspace() {
        return SessionWorkspace.getInstance();
    }
}
