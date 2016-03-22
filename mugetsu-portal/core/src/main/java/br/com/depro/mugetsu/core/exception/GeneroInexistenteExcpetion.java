package br.com.depro.mugetsu.core.exception;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;

/**
 * Classe de excecao de genero inexistente <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
public class GeneroInexistenteExcpetion extends CoreException {

	/** Nuemro de serie da classe */
	private static final long serialVersionUID = 7941464310668559721L;

	/**
	 * Construtor padrao da classe.
	 */
	public GeneroInexistenteExcpetion() {
		super();
	}

	/**
	 * @param codigoErro
	 */
	public GeneroInexistenteExcpetion(String codigoErro) {
		super(codigoErro);
	}

	/**
	 * @param cause
	 * @param codigoErro
	 */
	public GeneroInexistenteExcpetion(Throwable cause, String codigoErro) {
		super(cause, codigoErro);
	}

	/**
	 * @param cause
	 */
	public GeneroInexistenteExcpetion(Throwable cause) {
		super(cause);
	}

}
