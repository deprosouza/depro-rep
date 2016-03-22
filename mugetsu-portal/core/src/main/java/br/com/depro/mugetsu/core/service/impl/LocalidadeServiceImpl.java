package br.com.depro.mugetsu.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.LocalidadeDAO;
import br.com.depro.mugetsu.core.service.LocalidadeService;
import br.com.depro.mugetsu.model.Localidade;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26/06/2012
 */
@Service
public class LocalidadeServiceImpl extends TypezeroGenericServiceImpl<Localidade, LocalidadeDAO>
        implements LocalidadeService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.typezero.infrastructure.dao.DSphereGenericDAO)
     */
    @Autowired
    public void initDAO(LocalidadeDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see LocalidadeServiceImpl#listarTodasLocalidades()
     */
    public List<Localidade> listarTodasLocalidades() {
        return super.getDAO().obterPorTipo('P');
    }

    /**
     * @see LocalidadeServiceImpl#listarTodosIdiomas()
     */
    public List<Localidade> listarTodosIdiomas() {
        return super.getDAO().obterPorTipo('I');
    }
}
