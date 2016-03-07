package br.com.depro.fw.typezero.infrastructure.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.StringUtils;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.mail.AnexoExterno;
import br.com.depro.fw.typezero.infrastructure.model.mail.MailParameter;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 *
 * @author Rafael Souza - 26/06/2012
 *
 */
@Service
public class MailServiceImpl implements MailService {

	/**
	 * regex para validação de email
	 */
	private static final String REGEX_EMAIL = "[a-zA-Z_0-9\\.\\-]+@[a-zA-Z_0-9\\-]+\\.[a-zA-Z_0-9\\-]+(\\.[a-zA-Z_0-9]+)*";
	/**
	 * encode padrão que sera utilizado no conteudo do email
	 */
	private static final String CHAR_ENCODE = "UTF-8";
	/**
	 * utilizado para indicar parametros dinamicos (exemplo: !#parametroDinamico
	 * )
	 */
	private static final String INDICADOR_PARAMETRO = "!#";
	@Autowired
	private MailSessionService mailSessionService;
	private static final String PROP_USER = "mail.user";
	@Autowired
	private PropConfig mailConfiguration;

	/**
	 * <p>
	 * Metodo responsavel por substituir os parametros do template, identicos à
	 * chave do mapa, pelos valores do mapa de parametros. <br>
	 * <br>
	 *
	 * Ex:<br>
	 * map = ("chave", "template");<br>
	 * template = "Conteudo do !#chave que sera enviado por email.<br>
	 * <br>
	 *
	 * Após a substituição da !#chave pelo valor do mapa...<br>
	 * <br>
	 *
	 * Resultado: "Conteudo do <b>template</b> que sera enviado por email.<br>
	 *
	 * @param template
	 *            - Conteudo do template
	 * @param parametros
	 *            - parametros que substituirá os parametros dinamicos do
	 *            template
	 *            <p/>
	 * @return
	 */
	private String mergeParametrosTemplate(String template, Map<String, String> parametros) {
		if (parametros != null && parametros.size() > 0) {
			for (Map.Entry<String, String> element : parametros.entrySet()) {
				if (element.getKey() != null && element.getValue() != null) {
					template = template.replaceAll(INDICADOR_PARAMETRO + element.getKey(), element.getValue());
				}
			}
		}
		return template;
	}

	/**
	 * <p>
	 * Metodo responsavel por ler o conteudo do arquivo de template
	 *
	 * @param fileName
	 *            - Nome do arquivo do template (relativo a
	 *            webapp/email/template em common-bionexo)
	 * @param path
	 *            - caminho físico absoluto (obter pelo facesContext)
	 *            <p/>
	 * @return
	 */
	private String getMessageTemplate(String fileName, String path) {
		StringBuilder template = new StringBuilder();
		if (fileName != null) {

			// Configuracao por excecao
			if (StringUtils.isNotEmpty(path)) {
				try {
					File fileTemplate = new File(path + "/email/template/" + fileName);
					BufferedReader reader = new BufferedReader(new FileReader(fileTemplate));
					String linha = null;
					while ((linha = reader.readLine()) != null) {
						template.append(linha);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					InputStream is = this.getClass().getResourceAsStream("/br/com/email/template/" + fileName);
					if (is == null) {
						is = this.getClass().getClassLoader().getResourceAsStream("/br/com/email/template/" + fileName);
					}
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String linha = null;
					while ((linha = br.readLine()) != null) {
						template.append(linha);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return template.toString();
	}

	public void send(MailParameter parametrosEmail) throws ApplicationException {
		try {
			if (parametrosEmail == null) {
				Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, getMessage(MailServiceImpl.MessageKey.MAIL_NULL));
				throw new ApplicationException(getMessage(MailServiceImpl.MessageKey.DEFAULT_ErrorNode));
			}

			if (parametrosEmail.getNomeArquivoTemplate() == null || parametrosEmail.getNomeArquivoTemplate().trim().isEmpty()) {
				Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, getMessage(MailServiceImpl.MessageKey.TEMPLATE_NULL));
				throw new ApplicationException(getMessage(MailServiceImpl.MessageKey.DEFAULT_ErrorNode));
			}

			String corpoTemplate = "";
			String mensagem = "";

			String assunto = parametrosEmail.getAssunto();

			// Tratamento diferenciado por tipo de parser
			if (parametrosEmail.getTemplateParserType() == MailParameter.ParserType.REGEXP) {
				corpoTemplate = getMessageTemplate(parametrosEmail.getNomeArquivoTemplate(), parametrosEmail.getRealPath());
				if (corpoTemplate == null || corpoTemplate == "") {
					Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, getMessage(MailServiceImpl.MessageKey.TEMPLATE_NOT_FOUND, parametrosEmail.getNomeArquivoTemplate()));
					throw new ApplicationException(getMessage(MailServiceImpl.MessageKey.DEFAULT_ErrorNode));
				}
				mensagem = mergeParametrosTemplate(corpoTemplate, parametrosEmail.getParametrosMensagem());
			}

			// obtem uma sessao do email, com base no mail-config.properties
			// esta sessão terá as configurações para envio do email.
			Session sessao = mailSessionService.getSession();
			String mailUser = mailConfiguration.get(PROP_USER);
			final MimeMessage mimeMessage = new MimeMessage(sessao);

			mimeMessage.setSubject(assunto, CHAR_ENCODE);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setFileName(parametrosEmail.getNomeArquivoTemplate());

			if (isEmailValido(parametrosEmail.getMailFrom())) {
				mimeMessage.setFrom(new InternetAddress(parametrosEmail.getMailFrom()));
			} else {
				if (isEmailValido(mailUser)) {
					mimeMessage.setFrom(new InternetAddress(mailUser));
				}
			}

			addDestinatarios(mimeMessage, parametrosEmail.getDestinatarios(), MimeMessage.RecipientType.TO);
			addDestinatarios(mimeMessage, parametrosEmail.getDestinatariosCc(), MimeMessage.RecipientType.CC);
			addDestinatarios(mimeMessage, parametrosEmail.getDestinatariosCco(), MimeMessage.RecipientType.BCC);

			// emails a serem replicados. o delimitador é ';' (sem aspas).
			// Verificar mail-bionexo.properties
			String emailCco = "";

			if (emailCco != null) {
				addDestinatarios(mimeMessage, Arrays.asList(emailCco.split(";")), MimeMessage.RecipientType.BCC);
			}

			MimeMultipart multipart = new MimeMultipart("related");
			MimeBodyPart body = new MimeBodyPart();
			body.setText(mensagem, CHAR_ENCODE, "html");
			multipart.addBodyPart(body);

			embedImage(multipart, parametrosEmail.getImages(), parametrosEmail.getRealPath());

			embedAttachments(multipart, parametrosEmail.getAnexos(), parametrosEmail.getRealPath());
			embedExternalAttachments(multipart, parametrosEmail.getAnexosExternos());
			mimeMessage.setContent(multipart);

			new Thread(new Runnable() {

				public void run() {
					try {
						Transport.send(mimeMessage);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			throw new ApplicationException(getMessage(MailServiceImpl.MessageKey.DEFAULT_ErrorNode));
		}
	}

	/**
	 * <p>
	 * Adiciona os emails de um determinado tipo (mailTo, mailCC ou mailBCC) no
	 * MimeMessage que será enviado.
	 *
	 * @param mimeMessage
	 *            param destinatarios - lista dos emails que serão adicionados
	 *            conforme o tipo
	 * @param type
	 *            - RecipientType.TO, RecipientType.CC ou RecipientType.BCC
	 *            <p/>
	 * @throws Exception
	 */
	private void addDestinatarios(MimeMessage mimeMessage, List<String> destinatarios, RecipientType type) throws Exception {
		if (destinatarios != null) {
			for (String mail : destinatarios) {
				if (isEmailValido(mail)) {
					mimeMessage.addRecipient(type, new InternetAddress(mail));
				}
			}
		}
	}

	/**
	 * <p>
	 * Metodo responsavel por embutir os anexos no email
	 */
	private void embedAttachments(MimeMultipart multipart, List<String> anexos, String contextPath) {
		if (multipart != null && anexos != null && anexos.size() > 0) {
			for (String fileName : anexos) {
				try {
					if (fileName != null) {
						MimeBodyPart mimeAnexo = new MimeBodyPart();
						if (contextPath != null && !contextPath.trim().isEmpty()) {
							File fileAnexo = new File(contextPath + "/arquivos/" + fileName);
							if (fileAnexo.isFile()) {
								DataSource ds = new FileDataSource(fileAnexo);
								mimeAnexo.setDataHandler(new DataHandler(ds));
								mimeAnexo.setFileName(fileName);
								multipart.addBodyPart(mimeAnexo);
							}
						} else {
							DataSource ds = new URLDataSource(MailServiceImpl.class.getClassLoader().getResource("br/ss/spa/commons/email/arquivos/" + fileName));
							mimeAnexo.setDataHandler(new DataHandler(ds));
							mimeAnexo.setFileName(fileName);
							multipart.addBodyPart(mimeAnexo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // loop
		} // anexos
	}

	/**
	 * <p>
	 * Metodo responsavel por embutir os anexos no email
	 */
	private void embedExternalAttachments(MimeMultipart multipart, List<AnexoExterno> anexosExternos) {
		if (multipart != null && anexosExternos != null && anexosExternos.size() > 0) {
			for (AnexoExterno ext : anexosExternos) {
				try {
					if (ext != null) {
						DataSource ds = null;
						if (ext.getFileURL() != null || ext.getFile() != null) {
							File fileAnexo = null;
							fileAnexo = ext.getFile() != null ? ext.getFile() : new File(ext.getFileURL().getFile());

							if (fileAnexo.exists()) {
								ds = new ByteArrayDataSource(new FileInputStream(fileAnexo), "application/pdf");
							}

						} else if (ext.getInputStream() != null) {
							ds = new ByteArrayDataSource(ext.getInputStream(), "application/pdf");
						}

						MimeBodyPart mimeAnexo = new MimeBodyPart();
						mimeAnexo.setDataHandler(new DataHandler(ds));
						mimeAnexo.setFileName(ext.getName());
						multipart.addBodyPart(mimeAnexo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // loop
		} // anexos
	}

	/**
	 * <p>
	 * Metodo responsavel por embutir a imagem na mensagem de email <br>
	 * <br>
	 *
	 * Exemplo: <br>
	 * <br>
	 * Template html dentro de multipart = "< img src="cid:cabecalho.jpg" >
	 * " <br> imgName = "cabecalho.jpg"<br>
	 * path = "mail/"<br>
	 * Resultado = será embutido um arquivo de imagem do diretorio
	 * mail/cabecalho.jpg (relativo ao servidor) dentro da tag img cujo cid é
	 * igual a imgName ( cabecalho.jgp ).
	 *
	 * @param multipart
	 *            - parametro que sera setado no mimemessage antes de enviar o
	 *            email.
	 * @param imgName
	 *            - nome da imagem com extensão
	 * @param path
	 *            - caminho fisico do projeto(obter pelo facesContext)
	 *
	 */
	private void embedImage(MimeMultipart multipart, Map<String, String> images, String contextPath) {
		if (multipart != null && images != null && images.size() > 0) {
			try {
				for (Map.Entry<String, String> elemento : images.entrySet()) {
					MimeBodyPart bodyImg = new MimeBodyPart();
					if (contextPath != null && !contextPath.trim().isEmpty()) {
						File fileImg = new File(contextPath + "/email/img/" + elemento.getValue());
						if (fileImg.isFile()) {
							DataSource ds = new FileDataSource(fileImg);
							bodyImg.setDataHandler(new DataHandler(ds));
							bodyImg.setContentID("<" + elemento.getKey() + ">");
							multipart.addBodyPart(bodyImg);
						}
					} else {
						DataSource ds = new URLDataSource(MailServiceImpl.class.getClassLoader().getResource("br/ss/spa/commons/email/img/" + elemento.getValue()));
						bodyImg.setDataHandler(new DataHandler(ds));
						bodyImg.setContentID("<" + elemento.getKey() + ">");
						multipart.addBodyPart(bodyImg);
					}
				}
			} catch (Exception e) {
				// loogar
				e.printStackTrace();
			}
		}
	}

	/**
	 * <p>
	 * Metodo responsavel por verificar se o email é valido.
	 *
	 * @param email
	 *            <p/>
	 * @return
	 */
	private boolean isEmailValido(String email) {
		return (email != null && email.matches(REGEX_EMAIL));

	}

	/**
	 * <p>
	 * Enum que terá as chaves do i18n.properties
	 *
	 */
	private enum MessageKey {

		MAIL_NULL, TEMPLATE_NULL, TEMPLATE_NOT_FOUND, DEFAULT_ErrorNode;
	}

	/**
	 * <p>
	 * Metodo responsavel por retornar uma mensagem do i18n com base no tipo da
	 * mensagem.
	 *
	 * @param messageKey
	 *            - Tipo de erro que sera buscado no i18n
	 * @param parametros
	 *            - Parametros da mensagem do i18n. (não é obrigatorio passar
	 *            esse parametro).
	 *
	 * @return - Messagem
	 */
    private String getMessage(MailServiceImpl.MessageKey messageKey, Object... parametros) {
		String key = this.getClass().getSimpleName().concat("." + messageKey.name());
		return /*messageUtil.getMessage(key, parametros);*/ "";
	}
}
