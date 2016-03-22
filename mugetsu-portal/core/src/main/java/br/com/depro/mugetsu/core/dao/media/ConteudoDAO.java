package br.com.depro.mugetsu.core.dao.media;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.media.Episodio;
import br.com.depro.mugetsu.model.media.Media;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.07.2012
 */
@Service
public class ConteudoDAO extends TypezeroGenericJPADAO<Episodio> {

	/**
	 * Lista conteudo por media id
	 * 
	 * @param id
	 * @return Lista de entidades
	 * @throws TransactionBaseException Caso algum erro ocorra.
	 */
	@SuppressWarnings("unchecked")
	public List<Episodio> obterPorMediaId(Long id) throws TransactionBaseException {
		try {
			Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass());
			criteria.createAlias("media", "media")
					.add(Restrictions.eq("media.id", id));
			return criteria.list();
		} catch (HibernateException hexp) {
			throw new TransactionBaseException(hexp);
		}
	}
	
	/**
	 *  Lista conteudo por media
	 * 
	 * @param media
	 * @return Lista de entidades
	 * @throws TransactionBaseException Caso algum erro ocorra.
	 */
	public List<Episodio> obterPorMedia(Media media) throws TransactionBaseException {
		return super.obterPorCriteria(Restrictions.eq("media", media));
	}
}
