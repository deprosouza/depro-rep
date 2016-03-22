package br.com.depro.mugetsu.core.exception;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
public class SenhaInvalidaException extends ApplicationException {

    /** Numero de serie da classe */
    private static final long serialVersionUID = 5496903020416383554L;

    /**
     * Construtor padrao da classe.
     */
    public SenhaInvalidaException() {
        super();
    }

    /**
     * @param codigoErro
     */
    public SenhaInvalidaException(String codigoErro) {
        super(codigoErro);
    }

    /**
     * @param cause
     * @param codigoErro
     */
    public SenhaInvalidaException(Throwable cause, String codigoErro) {
        super(cause, codigoErro);
    }

    /**
     * @param cause
     */
    public SenhaInvalidaException(Throwable cause) {
        super(cause);
    }
}
