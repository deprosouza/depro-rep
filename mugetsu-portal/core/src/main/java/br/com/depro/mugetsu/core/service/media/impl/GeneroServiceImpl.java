package br.com.depro.mugetsu.core.service.media.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.media.GeneroDAO;
import br.com.depro.mugetsu.core.service.media.GeneroService;
import br.com.depro.mugetsu.model.media.Genero;

/**
 * @author rsouza
 * @version 1.0 - Versão Inicial - 28.06.2012
 */
@Service
public class GeneroServiceImpl extends TypezeroGenericServiceImpl<Genero, GeneroDAO> implements GeneroService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.dsphere.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(GeneroDAO dao) {
        super.setDAO(dao);
    }
}