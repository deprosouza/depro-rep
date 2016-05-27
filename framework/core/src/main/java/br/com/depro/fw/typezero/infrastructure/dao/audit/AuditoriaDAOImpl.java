package br.com.depro.fw.typezero.infrastructure.dao.audit;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.fw.typezero.infrastructure.model.audit.Auditoria;

/**
 * @author rsouza
 * @version 1.0 - versao inciao - 29.10.2015
 */
@Repository
public class AuditoriaDAOImpl extends TypezeroGenericJPADAO<Auditoria> implements AuditoriaDAO {

    @Override
    public List<Auditoria> findByAttrs(TypezeroCriteria criteriaRaw) {
        return null;
    }
}
