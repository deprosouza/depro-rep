package br.com.depro.mugetsu.negocio.service;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.security.Login;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.08.2012
 */
public interface PerfilService {

    /**
     * 
     * @param login
     * @return
     * @throws ApplicationException
     */
    List<Media> listraIndicacoesUsuario(Login login) throws ApplicationException;
}
