package br.com.depro.ffsniffer.core.linkshell.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.depro.ffsniffer.model.Linkshell;
import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.02.2015
 */
@Repository
public class LinkshellDAOImpl extends TypezeroGenericJPADAO<Linkshell> implements LinkshellDAO {

	/**
	 * Obtem linkshell pelo seu id lodestone
	 * @param id
	 * @return Entidade {@link Linkshell}
	 */
	public Linkshell findByIdLodestone(String id) {
		return super.executeCriteriaUnique(new ArrayList<Criterion>(Arrays.asList(Restrictions.eq("idLodestone", id))), null, null, null);
	}

    @Override
    public List<Linkshell> findByAttrs(TypezeroCriteria criteriaRaw) {
        return null;
    }
}
