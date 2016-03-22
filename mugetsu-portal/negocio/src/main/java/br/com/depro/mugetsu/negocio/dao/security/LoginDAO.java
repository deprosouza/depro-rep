package br.com.depro.mugetsu.negocio.dao.security;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.security.Login;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 24/06/2012
 */
@Service
public class LoginDAO extends TypezeroGenericJPADAO<Login> {

	/**
	 * Obtem usuario por username / email e senha
	 * 
	 * @param username
	 * @return Dados do usuario
	 * @throws TransactionBaseException Caso algum erro ocorra
	 */
	public Login obterPorUsername(String username) {
		return super.obterPorCriteriaUnique(Restrictions.or(
				Restrictions.eq("username", username),Restrictions.eq("email", username)));

	}
}
