package br.com.depro.fw.typezero.infrastructure.mail;

import javax.mail.Session;

/**
 * @author rsouza
 * @version $Id: MailSessionService.java 8982 2014-02-05 19:55:26Z rsouza $
 */
public interface MailSessionService {
    
    public Session getSession();
}
