package br.com.depro.mugetsu.negocio.service.media.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.model.media.Tema;
import br.com.depro.mugetsu.negocio.dao.media.TemaDAO;
import br.com.depro.mugetsu.negocio.service.media.TemaService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.08.2012
 */
@Service
public class TemaServiceImpl extends TypezeroGenericServiceImpl<Tema, TemaDAO> implements TemaService {

    @Autowired
    public void initDAO(TemaDAO dao) {
        super.setDAO(dao);
    }

    public Tema obterTemaPorPrefixo(String prefixo) throws CoreException {
        try {
            return super.getDAO().obterTemaPorNome(prefixo);
        } catch (TransactionBaseException texp) {
            throw new CoreException(texp);
        }
    }
}
