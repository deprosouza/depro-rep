package br.com.depro.fw.typezero.infrastructure.mail;


import java.util.Enumeration;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 * @author rsouza
 * @version $Id: MailSessionServiceImpl.java 8982 2014-02-05 19:55:26Z rsouza $
 */
@Service
public class MailSessionServiceImpl implements MailSessionService {

	private static final String PROP_USER = "mail.user";
	private static final String PROP_PASS = "mail.password";
	@Autowired
	private PropConfig mailConfiguration;

	public Session getSession() {
		final String username = mailConfiguration.get(PROP_USER);
		final String password = mailConfiguration.get(PROP_PASS);

		Properties props = new Properties();

		for (Enumeration propertyNames = mailConfiguration.getProperties().propertyNames(); propertyNames.hasMoreElements();) {
			Object key = propertyNames.nextElement();
			if (!PROP_USER.equals(key) && !PROP_PASS.equals(key) && key.toString().startsWith("mail")) {
				props.put(key, mailConfiguration.get(key.toString()));
			}
		}

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}
}
