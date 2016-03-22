package br.com.depro.mugetsu.core.service.security;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;
import br.com.depro.mugetsu.model.security.Permissao;
import br.com.depro.mugetsu.model.security.Role;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
public interface RoleService extends TypezeroGenericService<Role> {

    /**
     * Retorno role especificado
     *
     * @return Entidade de {@link Role}
     * @throws CoreException
     */
    Role obterRoleConvidado() throws CoreException;

    /**
     * Obtem role padrao de qualquer usuario que tenha abacado de se cadastrar
     * no sistema
     *
     * @return Entidade
     */
    Role buscarRolePadrao();

    /**
     * Relaciona os roles com as permissoes
     *
     * @param role
     * @param permissoes
     * @throws CoreException
     */
    void atualizarRolePermissoes(Role role, List<Permissao> permissoes) throws CoreException;
    
    /**
     * Busca roles com base nos tipos de acessos passados
     * @param List da Entidae Role 
     */
    List<Role> buscaRolesPorTipoAcesso(TipoAcessoEnum... tipoAcesso);
}