package br.com.depro.fw.typezero.infrastructure.mail;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.mail.MailParameter;

/**
 * @author rsouza
 * @version $Id: MailService.java 8982 2014-02-05 19:55:26Z rsouza $
 */
public interface MailService {

    public void send(MailParameter parametrosEmail) throws ApplicationException;
}
