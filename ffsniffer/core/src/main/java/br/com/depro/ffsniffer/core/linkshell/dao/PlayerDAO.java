package br.com.depro.ffsniffer.core.linkshell.dao;

import org.springframework.stereotype.Service;

import br.com.depro.ffsniffer.model.Player;
import br.com.depro.fw.typezero.infrastructure.dao.GenericDAO;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 05.02.2015
 */
@Service
public interface PlayerDAO extends GenericDAO<Player>  {

    Player findByIdLodestone(String id);
    
}
