package br.com.depro.mugetsu.negocio.dao.security;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.security.Permissao;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
@Service
public class PermissaoDAO extends TypezeroGenericJPADAO<Permissao> {

	/**
	 * Lista todas as permissões de um role especifico
	 * 
	 * @param id
	 * @return Lista de entidades
	 * @throws TransactionBaseException
	 */
	@SuppressWarnings("unchecked")
	public List<Permissao> obterPermissoesPorIdRole(Long id) throws TransactionBaseException {
		try {
			Query query = super.getEntityManager().createQuery("SELECT p FROM Permissao p JOIN p.roles r WHERE r.id = :idRole");
			query.setParameter("idRole", id);
			return query.getResultList();
		} catch (Exception hexp) {
			throw new TransactionBaseException(hexp);
		}
	}
}
