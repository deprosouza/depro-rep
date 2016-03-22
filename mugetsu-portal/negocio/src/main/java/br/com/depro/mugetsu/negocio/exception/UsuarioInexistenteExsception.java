package br.com.depro.mugetsu.negocio.exception;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10/05/2012
 */
public class UsuarioInexistenteExsception extends ApplicationException {

    /** Numero de serie da classe */
    private static final long serialVersionUID = 6231317142874397230L;

    /**
     * Construtor padrao da classe.
     */
    public UsuarioInexistenteExsception() {
        super();
    }

    /**
     * @param codigoErro
     */
    public UsuarioInexistenteExsception(String codigoErro) {
        super(codigoErro);
    }

    /**
     * @param cause
     * @param codigoErro
     */
    public UsuarioInexistenteExsception(Throwable cause, String codigoErro) {
        super(cause, codigoErro);
    }

    /**
     * @param cause
     */
    public UsuarioInexistenteExsception(Throwable cause) {
        super(cause);
    }
}
