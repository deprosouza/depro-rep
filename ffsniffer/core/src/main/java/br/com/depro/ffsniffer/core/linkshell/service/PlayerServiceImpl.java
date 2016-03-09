package br.com.depro.ffsniffer.core.linkshell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.ffsniffer.core.linkshell.dao.PlayerDAO;
import br.com.depro.ffsniffer.model.Player;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 05.02.2015
 */
@Service
public class PlayerServiceImpl extends TypezeroGenericServiceImpl<Player, PlayerDAO> implements PlayerService {

	@Autowired
	public void initDAO(PlayerDAO dao) {
		super.setDAO(dao);
	}
	
	public Player buscarPorIdLodestone(String id) {
		return getDAO().findByIdLodestone(id);
	}
}
