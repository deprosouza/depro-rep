package br.com.depro.mugetsu.negocio.service.perfil.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.dao.perfil.ProjetoConteudoDAO;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoConteudoService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 02.09.2012
 */
@Service
public class ProjetoConteudoServiceImpl extends TypezeroGenericServiceImpl<ProjetoConteudo, ProjetoConteudoDAO> implements ProjetoConteudoService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericDAO)
     */
    @Autowired
    public void initDAO(ProjetoConteudoDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see ProjetoConteudoService#listarProjetosNaoAssistidos(Login)
     */
    public Integer buscarQuantidadeNaoAssistidos(Login login) {
        return super.getDAO().findCountNaoAssistidos(login);
    }
}
