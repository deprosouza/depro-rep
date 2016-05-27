package br.com.depro.fw.typezero.infrastructure.bean.utils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilitario para tratamento de mensagens internacionalizadas.
 *
 * @author Fernando Moraes
 * @version $Id: MessageUtil.java 2790 2012-05-07 13:24:02Z fernando.moraes $
 */
public class MessageUtil implements Serializable {

    private static final long serialVersionUID = -7095132442098037291L;
    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);
    private static final String I18N_FILENAME = ".i18n";
    private static final Pattern patternPackageAscore = Pattern.compile("^(br\\.com.*?\\.(model|core|ws|web|infrastructure))");
    private String nomeBaseResourceBundle;
    private ResourceBundle resourceBundle;
    private Locale localeBundle;

    /**
     * Construtor com uma classe que sera utilizada para resgatar os valores internacionalizados.<br/>
     * A localizacao do arquivo eh definido pelo pacote da classe informada concatenada com o nome padrao "i18n".<br/>
     * Sera utilizado o {@link Locale} {@link Locale#getDefault() padrao} da aplicacao.
     *
     * @param clazz classe utilizada para construir o nome base (base-name) dos valores internacionalizados.
     */
    public MessageUtil(Class<?> clazz) {
        this(clazz, Locale.getDefault());
    }

    /**
     * Construtor com uma classe que sera utilizada para resgatar os valores internacionalizados.<br/>
     * A localizacao do arquivo eh definido pelo pacote da classe informada concatenada com o nome padrao "i18n".<br/>
     *
     * @param clazz classe utilizada para construir o nome base (base-name) dos valores internacionalizados.
     * @param locale o {@link Locale} que sera utilizado para resgatar os valores internacionalizados.
     */
    public MessageUtil(Class<?> clazz, Locale locale) {
        String nomeBase = clazz.getPackage().getName();
        Matcher matcher = patternPackageAscore.matcher(nomeBase);
        if (matcher.find()) {
            nomeBase = matcher.group(1).concat(I18N_FILENAME);
        }
        this.localeBundle = locale;
        this.nomeBaseResourceBundle = nomeBase;
    }

    /**
     * Construtor com o nome base (base-name) definido para os valores internacionalizados.<br/>
     * Sera utilizado o {@link Locale} {@link Locale#getDefault() padrao} da aplicacao.
     *
     * @param nomeBaseResourceBundle o nome base (base-name) dos valores internacionalizados.
     */
    public MessageUtil(String nomeBaseResourceBundle) {
        this(nomeBaseResourceBundle, Locale.getDefault());
    }

    /**
     * Construtor com o nome base (base-name) definido para os valores internacionalizados.<br/>
     *
     * @param nomeBaseResourceBundle o nome base (base-name) dos valores internacionalizados.
     * @param locale o {@link Locale} que sera utilizado para resgatar os valores internacionalizados.
     */
    public MessageUtil(String nomeBaseResourceBundle, Locale locale) {
        this.localeBundle = locale;
        this.nomeBaseResourceBundle = nomeBaseResourceBundle;
    }

    /**
     * Recupera a mensagem a partir do resource-bundle informado.
     *
     * @param key a chave da mensagem no resource-bundle.
     * @return a mensagem para a chave informada.
     */
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     * Recupera a mensagem a partir do resource-bundle informado.
     *
     * @param key a chave da mensagem no resource-bundle.
     * @param args argumentos para substituir na mensagem.
     * @return a mensagem para a chave informada.
     */
    public String getMessage(String key, Object[] args) {
        String message = key;
        try {
            if (resourceBundle == null) {
                resourceBundle = ResourceBundle.getBundle(nomeBaseResourceBundle, localeBundle);
            }
            message = resourceBundle.getString(key);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    Object obj = args[i];
                    if (obj instanceof String) {
                        try {
                            String text = resourceBundle.getString((String) obj);
                            args[i] = text;
                        } catch (Exception e) {
                        }
                    }

                }
                message = MessageFormat.format(message, args);
            }
        } catch (Exception e) {
            logger.warn(nomeBaseResourceBundle + ": " + e.getMessage());
        }
        return message;
    }

    /**
     * Recupera a mensagem a partir do resource-bundle informado.
     *
     * @param enumValue o {@link Enum} para compor a chave da mensagem no resource-bundle.
     * @return a mensagem para a chave informada.
     */
    public String getMessage(Enum<?> enumValue) {
        return getMessage(enumValue, null);
    }

    /**
     * Recupera a mensagem a partir do resource-bundle informado.
     *
     * @param enumValue o {@link Enum} para compor a chave da mensagem no resource-bundle.
     * @param args argumentos para substituir na mensagem.
     * @return a mensagem para a chave informada.
     */
    public String getMessage(Enum<?> enumValue, Object[] args) {
        String key = enumValue.getClass().getSimpleName().concat("." + enumValue.name());
        return this.getMessage(key, args);
    }

    /**
     * Retorna o {@link Locale} configurado na instância corrente.
     * 
     * @return o {@link Locale} configurado.
     */
    public Locale getLocale() {
        return localeBundle;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("nomeBaseResourceBundle", nomeBaseResourceBundle)
                .append("localeBundle", localeBundle)
                .toString();
    }
}
