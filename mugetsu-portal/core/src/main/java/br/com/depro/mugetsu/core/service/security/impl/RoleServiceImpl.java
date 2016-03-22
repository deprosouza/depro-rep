package br.com.depro.mugetsu.core.service.security.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.security.RoleDAO;
import br.com.depro.mugetsu.core.service.security.PermissaoService;
import br.com.depro.mugetsu.core.service.security.RoleService;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;
import br.com.depro.mugetsu.model.security.Permissao;
import br.com.depro.mugetsu.model.security.Role;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
@Service
public class RoleServiceImpl extends TypezeroGenericServiceImpl<Role, RoleDAO> implements RoleService {

    @Autowired
    private PermissaoService permissaoService;

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.dsphere.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(RoleDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see RoleService#obterRoleConvidado(String)
     */
    public Role obterRoleConvidado() throws CoreException {
        return super.getDAO().obterPorNome("NO_ROLE");
    }

    /**
     * @see RoleService#buscarRolePadrao()
     */
    public Role buscarRolePadrao() {
        return super.getDAO().obterPorNome("ROLE_USER");
    }

    /**
     * @see RoleService#atualizarRolePermissoes(List)
     */
    public void atualizarRolePermissoes(Role role, List<Permissao> permissoes)
            throws CoreException {
        try {
            Set<Permissao> novaListaPermissao = new HashSet<Permissao>();
            List<Permissao> todasPermissoes = permissaoService.buscarTodas();
            //Collections.sort(todasPermissoes);
            Collections.reverse(todasPermissoes);
            for (Permissao permissao : permissoes) {
                novaListaPermissao.add(permissao);
                Long parentID = permissao.getNivel();
                for (Permissao parent : todasPermissoes) {
                    if (parent.getId().equals(parentID)) {
                        novaListaPermissao.add(parent);
                        parentID = parent.getId();
                    }
                }
            }
            super.getDAO().refresh(role);
            role.getPermissoes().clear();
            role.getPermissoes().addAll(novaListaPermissao);
            super.getDAO().atualizar(role);

        } catch (TransactionBaseException texp) {
            throw new CoreException(texp);
        }
    }

    public List<Role> buscaRolesPorTipoAcesso(TipoAcessoEnum... tipoAcesso) {
        return getDAO().findByTipoAcesso(tipoAcesso);
    }
}
