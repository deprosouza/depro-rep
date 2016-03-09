package br.com.depro.ffsniffer.core.linkshell.service;

import br.com.depro.ffsniffer.model.Linkshell;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;

/**
 * @author rsouza
 * @version 1.0 - Versao Iniciao - 26.02.2015
 */
public interface LinkshellService extends TypezeroGenericService<Linkshell> {

	/**
	 * Busca {@link Linkshell} pelo seu id lodestone
	 * @param id
	 * @return Entidade {@link Linkshell}
	 */
	Linkshell buscarPorIdLodestone(String id);
}
