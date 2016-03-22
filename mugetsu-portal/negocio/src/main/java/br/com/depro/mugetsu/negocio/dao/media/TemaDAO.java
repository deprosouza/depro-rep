package br.com.depro.mugetsu.negocio.dao.media;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.media.Tema;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.08.2012
 */
@Service
public class TemaDAO extends TypezeroGenericJPADAO<Tema> {
	
	/**
	 * Obtem entidade pelo nome do tema
	 * 
	 * @param nome
	 * @return Entidade 
	 * @throws TransactionBaseException Caso algum erro ocorra
	 */
	public Tema obterTemaPorNome(String nome) throws TransactionBaseException {
		return obterPorCriteriaUnique(Restrictions.eq("prefixo", nome));
	}
}
