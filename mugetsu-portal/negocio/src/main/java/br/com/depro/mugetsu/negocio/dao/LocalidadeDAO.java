package br.com.depro.mugetsu.negocio.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.Localidade;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26/06/2012
 */
@Service
public class LocalidadeDAO extends TypezeroGenericJPADAO<Localidade> {

	/**
	 * Obtem as lovalidades ou paises
	 * 
	 * @param tipo
	 * @return Lista de Entidades de Localidade
	 * @throws TransactionBaseException Caso algum erro ocorra.
	 */
	public List<Localidade> obterPorTipo(char tipo) {
		return super.obterPorCriteria(Restrictions.eq("tipoLocalidade", tipo));
	}
}
