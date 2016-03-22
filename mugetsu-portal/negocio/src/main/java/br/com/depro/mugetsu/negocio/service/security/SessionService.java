package br.com.depro.mugetsu.negocio.service.security;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.security.Session;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.07.2012
 */
public interface SessionService extends TypezeroGenericService<Session> {

	/**
	 * Obtem um sessao do usuario informado
	 * 
	 * @param username
	 * @param senha
	 * @param enderecoIP
	 * @return {@link Session}
	 * @throws ApplicationException Caso Usuario nao exista.
	 */
	Session efetuarLogin(String username, String senha, String enderecoIP) throws ApplicationException;
	
	/**
	 * Encerra a sessão do usuário
	 * 
	 * @param session
	 * @throws ApplicationException Caso algum erro ocorra;
	 */
	void efetuarLoggout(Session session) throws ApplicationException;
}
