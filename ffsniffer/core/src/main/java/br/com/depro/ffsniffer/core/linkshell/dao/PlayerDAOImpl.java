package br.com.depro.ffsniffer.core.linkshell.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.ffsniffer.model.Player;
import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 05.02.2015
 */
@Service
public class PlayerDAOImpl extends TypezeroGenericJPADAO<Player> implements PlayerDAO {

	/**
	 * Obtem entidade {@link Player} pelo seu id Lodestone informado
	 * @param id
	 * @return Enitidade {@link Player}
	 */
	public Player findByIdLodestone(String id) {
	    return super.executeCriteriaUnique(new ArrayList<Criterion>(Arrays.asList(Restrictions.eq("idLodestone", id))), null, null, null);
	}

    @Override
    public List<Player> findByAttrs(TypezeroCriteria criteriaRaw) {
        return null;
    }
}
