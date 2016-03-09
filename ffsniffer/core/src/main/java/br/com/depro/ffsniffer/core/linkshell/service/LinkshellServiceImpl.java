package br.com.depro.ffsniffer.core.linkshell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.ffsniffer.core.linkshell.dao.LinkshellDAO;
import br.com.depro.ffsniffer.model.Linkshell;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;

/**
 * @author rsouza
 * @version 1.0 - Versao Iniciao - 26.02.2015
 */
@Service
public class LinkshellServiceImpl extends TypezeroGenericServiceImpl<Linkshell, LinkshellDAO> implements LinkshellService {

	@Autowired
	public void initDAO(LinkshellDAO dao) {
		super.setDAO(dao);
	}

	/**
	 * @see LinkshellService#buscarPorIdLodestone(String)
	 */
	public Linkshell buscarPorIdLodestone(String id) {
		return getDAO().findByIdLodestone(id);
	}

}
