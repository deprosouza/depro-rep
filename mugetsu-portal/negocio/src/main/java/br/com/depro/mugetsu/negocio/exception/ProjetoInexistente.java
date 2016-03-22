package br.com.depro.mugetsu.negocio.exception;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.07.2012
 */
public class ProjetoInexistente extends CoreException {

	/** Numero de serie da classe */
	private static final long serialVersionUID = -3307614097511650106L;

	/**
	 * 
	 */
	public ProjetoInexistente() {
		super();
	}

	/**
	 * @param codigoErro
	 */
	public ProjetoInexistente(String codigoErro) {
		super(codigoErro);
	}

	/**
	 * @param cause
	 * @param codigoErro
	 */
	public ProjetoInexistente(Throwable cause, String codigoErro) {
		super(cause, codigoErro);
	}

	/**
	 * @param cause
	 */
	public ProjetoInexistente(Throwable cause) {
		super(cause);
	}
}
