package br.com.depro.fw.typezero.infrastructure.service;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * 
 * @author rsouza
 *
 * @param <T>
 */
public interface TypezeroGenericService<T extends EntidadeBase> {

	/**
	 * Persiste entidade
	 * 
	 * @param entidade
	 * @return TODO
	 * @throws CoreException Caso algum erro ocorra
	 */
	T salvar(T entidade) throws ApplicationException;
	
	/**
	 * Atializa entidade
	 * 
	 * @param entidade
	 * @return TODO
	 * @throws CoreException Caso algum erro ocorra
	 */
	T atualizar(T entidade) throws ApplicationException;
	
	/**
	 * Exclui entidade
	 * 
	 * @param entidade
	 * @throws CoreException Caso algum erro ocorra.
	 */
	void excluir(T entidade) throws ApplicationException;
	
	/**
	 * Exclui entidade pelo id
	 * 
	 * @param id
	 * @throws CoreException Caso algum erro ocorra.
	 */
	void excluirPorId(Long id) throws ApplicationException;
	
	/**
	 * Atualiza entidade
	 * 
	 * @param entidade
	 * @return TODO
	 * @throws CoreException Caso algum erro ocorra.
	 */
	T refresh(T entidade);
	
	/**
	 * Obtem entidade por ID
	 * 
	 * @param id
	 * @return Entidade
	 * @throws CoreException Caso algum erro ocorra
	 */
	T buscarPorId(Long id) throws ApplicationException;
	
	/**
	 * Obtem entidade por ID
	 * 
	 * @param id
	 * @return Entidade
	 * @throws CoreException Caso algum erro ocorra
	 */
	T buscarPorIdSimples(Long id);
	
	/**
	 * Lista todas as entidades
	 * 
	 * @return Lista de entidades
	 */
	List<T> buscarTodos();
	
	/**
     * Lista das entidades pelos atributos
     * 
     * @return Lista de entidades
     */
    List<T> buscarPorAtributos(TypezeroCriteria criteriaRaw);
    
    /**
     * Lista das entidades pelos atributos
     * 
     * @return Uma entidade
     */
    T buscarUmPorAtributos(TypezeroCriteria criteriaRaw);
	
}
