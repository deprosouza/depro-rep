package br.com.depro.mugetsu.core.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.security.PermissaoDAO;
import br.com.depro.mugetsu.core.service.security.PermissaoService;
import br.com.depro.mugetsu.model.security.Permissao;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
@Service
public class PermissaoServiceImpl extends TypezeroGenericServiceImpl<Permissao, PermissaoDAO> implements PermissaoService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.dsphere.mugetsu.web.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(PermissaoDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see PermissaoService#obterPermissoesPorIdRole(Long)
     */
    public List<Permissao> obterPermissoesPorIdRole(Long id)
            throws CoreException {
        try {
            return super.getDAO().obterPermissoesPorIdRole(id);
        } catch (TransactionBaseException tbexp) {
            throw new CoreException(tbexp);
        }
    }
}
