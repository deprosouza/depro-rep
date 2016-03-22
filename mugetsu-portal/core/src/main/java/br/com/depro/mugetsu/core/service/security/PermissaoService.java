package br.com.depro.mugetsu.core.service.security;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.security.Permissao;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
public interface PermissaoService extends TypezeroGenericService<Permissao> {
	
	/**
	 * Retonar todas as permissões de um role especifico
	 * 
	 * @param id
	 * @return Lista de entidades
	 * @throws CoreException Caso algum erro ocorra
	 */
	List<Permissao> obterPermissoesPorIdRole(Long id) throws CoreException;
}
