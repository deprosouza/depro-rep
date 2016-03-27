package br.com.depro.mugetsu.core.media.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.mugetsu.model.media.AlternativeName;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 23.03.2016
 */
@Repository
public class AlternativeNameDAOImpl extends TypezeroGenericJPADAO<AlternativeName> implements AlternativeNameDAO {

	@Override
	public List<AlternativeName> findByAttrs(TypezeroCriteria criteriaRaw) {
		return null;
	}
}
