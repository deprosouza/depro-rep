package br.com.depro.ffsniffer.core.linkshell.dao;

import br.com.depro.ffsniffer.model.Linkshell;
import br.com.depro.fw.typezero.infrastructure.dao.GenericDAO;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.02.2015
 */
public interface LinkshellDAO extends GenericDAO<Linkshell>  {
    
    Linkshell findByIdLodestone(String id);
}
