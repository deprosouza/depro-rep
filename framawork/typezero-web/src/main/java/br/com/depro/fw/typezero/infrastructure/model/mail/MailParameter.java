package br.com.depro.fw.typezero.infrastructure.model.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rsouza
 * @version $Id: MailParameter.java 8982 2014-02-05 19:55:26Z gabriel $
 */
public class MailParameter implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nomeArquivoTemplate;
    private String assunto;
    private List<String> destinatarios;
    private List<String> destinatariosCc;
    private List<String> destinatariosCco;
    private Map<String, String> parametrosMensagem;
    private List<String> anexos;
    private List<AnexoExterno> anexosExternos;
    private Map<String, String> images = new HashMap<String, String>();
    private String mailFrom;
    private String realPath;
    private ParserType templateParserType = ParserType.REGEXP;

    public enum ParserType {
        REGEXP, VELOCITY
    }

    public MailParameter(String nomeArquivoTemplate, String assunto, String realPath) {
        super();
        this.nomeArquivoTemplate = nomeArquivoTemplate;
        this.assunto = assunto;
        this.realPath = realPath;
    }

    public Map<String, String> getParametrosMensagem() {
        if (parametrosMensagem == null) {
            parametrosMensagem = new HashMap<String, String>();
        }
        return parametrosMensagem;
    }

    public void addParam(String key, String value) {
        getParametrosMensagem().put(key, value);
    }

    public void setParametrosMensagem(Map<String, String> parametrosMensagem) {
        this.parametrosMensagem = parametrosMensagem;
    }

    public List<String> getDestinatarios() {
        if (destinatarios == null) {
            destinatarios = new ArrayList<String>();
        }
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public List<String> getDestinatariosCc() {
        if (destinatariosCc == null) {
            destinatariosCc = new ArrayList<String>();
        }
        return destinatariosCc;
    }

    public void setDestinatariosCc(List<String> destinatariosCc) {
        this.destinatariosCc = destinatariosCc;
    }

    public List<String> getDestinatariosCco() {
        if (destinatariosCco == null) {
            destinatariosCco = new ArrayList<String>();
        }
        return destinatariosCco;
    }

    public void setDestinatariosCco(List<String> destinatariosBcc) {
        this.destinatariosCco = destinatariosBcc;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public List<String> getAnexos() {
        if (anexos == null) {
            anexos = new ArrayList<String>();
        }
        return anexos;
    }

    public void setAnexos(List<String> anexos) {
        this.anexos = anexos;
    }

    public List<AnexoExterno> getAnexosExternos() {
        if (anexosExternos == null) {
            anexosExternos = new ArrayList<AnexoExterno>();
        }
        return anexosExternos;
    }

    public void setAnexosExternos(List<AnexoExterno> anexosExternos) {
        this.anexosExternos = anexosExternos;
    }

    /**
     * <p>
     * caminho fisico completo (obter o realPath pelo facesContext)
     */
    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public String getNomeArquivoTemplate() {
        return nomeArquivoTemplate;
    }

    public void setNomeArquivoTemplate(String nomeArquivoTemplate) {
        this.nomeArquivoTemplate = nomeArquivoTemplate;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public ParserType getTemplateParserType() {
        return templateParserType;
    }

    public void setTemplateParserType(ParserType templateParserType) {
        this.templateParserType = templateParserType;
    }
}
