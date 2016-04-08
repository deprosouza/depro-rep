package br.com.depro.fw.typezero.infrastructure.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Classe de autenticacao de email. Utilizado para o caso do servidor de email
 * possuir autenticacao com usuario e senha.
 * 
 * @author samuelmd
 * 
 */
public class SMTPAuthenticator extends Authenticator {

    private String username;
    private String password;

    public SMTPAuthenticator(String userName, String password) {
        if (userName == null) {
            userName = "";
        }
        if (password == null) {
            password = "";
        }
        this.username = userName;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
