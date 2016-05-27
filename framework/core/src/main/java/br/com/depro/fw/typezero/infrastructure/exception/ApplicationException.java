package br.com.depro.fw.typezero.infrastructure.exception;

import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.utils.FWCode;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.07.2012
 */
public class ApplicationException extends Exception {

	/** Numero de serie da classe */
	private static final long serialVersionUID = -1618017352575543227L;
	private static final String VAZIO = "";
	private String codigoErro = FWCode.FW0000.name();

	/**
	 * Construtor padrao da classe.
	 */
	public ApplicationException() {
		super(FWCode.FW0000.name());
	}

	/**
	 * @param message
	 */
	public ApplicationException(String codigoErro) {
		super(VAZIO);
		this.codigoErro = codigoErro;
	}

	/**
	 * @param message
	 */
	public ApplicationException(String codigoErro, String mensagem) {
		super(mensagem);
		this.codigoErro = codigoErro;
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause, String codigoErro) {
		super((cause instanceof ApplicationException ? ((ApplicationException) cause).getMensagemOrigina() : cause.getMessage()), cause);
		this.codigoErro = codigoErro;
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause, String codigoErro, String mensagem) {
		super(mensagem, cause);
		this.codigoErro = codigoErro;
	}

	/**
     * @param cause
     */
    public ApplicationException(Throwable cause) {
    	super((cause instanceof ApplicationException ? ((ApplicationException) cause).getMensagemOrigina() : cause.getMessage()), cause);
        if (cause instanceof ApplicationException) {
        	ApplicationException throwable =  ((ApplicationException) cause);
        	this.codigoErro = throwable.getCodigoErro();
        } else {
        	this.codigoErro = FWCode.FW0000.name();
        }
    }

	/**
	 * Retorna a mensagem formatada com codigo de erro
	 */
	public String getMessage() {
		String mensagem = super.getMessage();
		String codigo = "[" + this.codigoErro + "]";
		if (StringUtils.isBlank(mensagem)) {
			mensagem = codigo;
		} else {
			mensagem = codigo + " " + mensagem;
		}
		return mensagem;
	}

	/**
	 * @return the mensagemOrigina
	 */
	public String getMensagemOrigina() {
		return super.getMessage();
	}

	/**
	 * @return the codigoErro
	 */
	public String getCodigoErro() {
		return codigoErro;
	}

	/**
	 * @param codigoErro
	 *            the codigoErro to set
	 */
	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

}
