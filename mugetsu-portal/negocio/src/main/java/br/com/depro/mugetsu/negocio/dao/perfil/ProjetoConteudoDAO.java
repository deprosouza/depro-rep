package br.com.depro.mugetsu.negocio.dao.perfil;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 02.09.2012
 */
@Service
public class ProjetoConteudoDAO extends TypezeroGenericJPADAO<ProjetoConteudo> {

    /**
     * Lista entidades nao assitidas pelo login
     *
     * @param login
     * @return Lista de entidades
     */
    public Integer findCountNaoAssistidos(Login login) {
        StringBuilder queryMaker = new StringBuilder();
        queryMaker.append("SELECT count(pc) FROM Projeto p JOIN p.conteudosProjeto pc JOIN pc.conteudo c ");
        queryMaker.append("WHERE p.usuario = :login AND pc.baixado = :baixado AND pc.assistido = :assitido ");
        queryMaker.append("ORDER BY c.id asc, pc.dataDownload DESC");
        Query query = super.getEntityManager().createQuery(queryMaker.toString());
        query.setParameter("login", login);
        query.setParameter("baixado", true);
        query.setParameter("assitido", false);
        return Long.valueOf((Long) query.getSingleResult()).intValue();
    }
}
