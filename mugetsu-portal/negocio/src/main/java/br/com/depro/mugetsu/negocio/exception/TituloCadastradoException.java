package br.com.depro.mugetsu.negocio.exception;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;

/**
 * Classe de excecao de insersao de titulo midia <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
public class TituloCadastradoException extends CoreException {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 4198561553576724671L;

	/**
	 * Construtor padrao da classe.
	 */
	public TituloCadastradoException() {
		super();
	}

	/**
	 * @param codigoErro
	 */
	public TituloCadastradoException(String codigoErro) {
		super(codigoErro);
	}

	/**
	 * @param cause
	 * @param codigoErro
	 */
	public TituloCadastradoException(Throwable cause, String codigoErro) {
		super(cause, codigoErro);
	}

	/**
	 * @param cause
	 */
	public TituloCadastradoException(Throwable cause) {
		super(cause);
	}

}
