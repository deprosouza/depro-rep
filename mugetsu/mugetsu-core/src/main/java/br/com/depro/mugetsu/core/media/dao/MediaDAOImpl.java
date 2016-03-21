package br.com.depro.mugetsu.core.media.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.mugetsu.model.media.Media;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 20.03.2016
 */
@Repository
public class MediaDAOImpl extends TypezeroGenericJPADAO<Media> implements MediaDAO {

	@Override
	public List<Media> findByAttrs(TypezeroCriteria criteriaRaw) {
		return null;
	}
}
