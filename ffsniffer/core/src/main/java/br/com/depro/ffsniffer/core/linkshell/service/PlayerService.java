package br.com.depro.ffsniffer.core.linkshell.service;

import br.com.depro.ffsniffer.model.Player;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 05.02.2015
 */
public interface PlayerService extends TypezeroGenericService<Player> {

	/**
	 * Buscar player pelo seu id Lodestone
	 * @param id
	 * @return Entidade {@link Player}
	 */
	Player buscarPorIdLodestone(String id);
	
}
