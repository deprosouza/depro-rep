package br.com.depro.mugetsu.negocio.dao.security;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.mugetsu.model.security.Ativacao;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.09.2012
 */
@Service
public class AtivacaoDAO extends TypezeroGenericJPADAO<Ativacao> {

    /**
     * Obtem entidade pelo codigo de ativacao
     *
     * @param codigo
     * @return Entidade
     */
    public Ativacao obterPorCondigoAtivacao(String codigo) {
        return super.obterPorCriteriaUnique(Restrictions.eq("codigoAtivacao", codigo));
    }
}
