package br.com.depro.mugetsu.core.service.media.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.media.BroadcastDAO;
import br.com.depro.mugetsu.core.service.media.BroadcastService;
import br.com.depro.mugetsu.model.media.broadcast.BroadcastBase;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Inicial - 30.06.2012
 */
@Service
public class BroadcastServiceImpl extends TypezeroGenericServiceImpl<BroadcastBase, BroadcastDAO>
        implements BroadcastService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.dsphere.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(BroadcastDAO dao) {
        super.setDAO(dao);
    }
}
