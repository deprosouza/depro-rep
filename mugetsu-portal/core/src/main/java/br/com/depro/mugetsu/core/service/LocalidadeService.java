package br.com.depro.mugetsu.core.service;

import java.util.List;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.Localidade;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versão Inicial - 26/06/2012
 */
public interface LocalidadeService extends TypezeroGenericService<Localidade> {
	
	/**
	 * Lista todos os Localidades cadastrados na base
	 * 
	 * @return List de {@link Localidade}
	 * @throws CoreException Caso algum erro ocorra
	 */
	List<Localidade> listarTodasLocalidades();
	
	/**
	 * Lista todos os idiomas
	 * 
	 * @return Lista de idiomas
	 * @throws CoreException Caso algum erro ocorra.
	 */
	List<Localidade> listarTodosIdiomas();
}

