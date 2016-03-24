package br.com.depro.mugetsu.core.media.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.mugetsu.model.media.Tag;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 22.03.2016
 */
@Repository
public class TagDAOImpl extends TypezeroGenericJPADAO<Tag> implements TagDAO {

	@Override
	public List<Tag> findByAttrs(TypezeroCriteria criteriaRaw) {
		return null;
	}

}
