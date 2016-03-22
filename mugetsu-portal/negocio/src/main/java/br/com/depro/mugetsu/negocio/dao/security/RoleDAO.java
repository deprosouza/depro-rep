package br.com.depro.mugetsu.negocio.dao.security;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;
import br.com.depro.mugetsu.model.security.Role;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Incial - 30.06.2012
 */
@Service
public class RoleDAO extends TypezeroGenericJPADAO<Role> {

    /**
     * Obtem role pelo nome especificado
     *
     * @param role
     * @return Entidade
     */
    public Role obterPorNome(String role) {
        return obterPorCriteriaUnique(Restrictions.eq("prefixo", role));
    }
    
    /**
     * Obtem todos os Roles do tipo de acesso especificado
     * @param tipoAcessoEnums
     * @return
     * @throws TransactionBaseException 
     */
    public List<Role> findByTipoAcesso(TipoAcessoEnum... tipoAcessoEnums) {
        return obterPorCriteria(Restrictions.in("tipoAcesso", tipoAcessoEnums));
    }
}
