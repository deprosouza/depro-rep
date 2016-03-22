package br.com.depro.mugetsu.negocio.dao.perfil;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 23.07.02012
 */
@Service
public class ProjetoDAO extends TypezeroGenericJPADAO<Projeto> {

    /**
     * Retorna projeto do usuario caso exista
     *
     * @param login
     * @param media
     * @return
     * @throws TransactionBaseException Caso Algum erro ocorra
     */
    public Projeto obterProjetoUsuario(Login login, Media media) throws TransactionBaseException {
        return super.obterPorCriteriaUnique(Restrictions.eq("media", media), Restrictions.eq("usuario", login));
    }

    /**
     * Obtem lista de projetos do usuario especificado
     *
     * @param login
     * @return Lista de entidades
     * @throws TransactionBaseException Caso algum erro ocorra
     */
    @SuppressWarnings("unchecked")
    public List<Projeto> listarPorUsuario(Login login) throws TransactionBaseException {
        Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass());
        criteria.add(Restrictions.eq("usuario", login));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    /**
     * Lista todas as entidades de um usuario pela letra
     *
     * @param login
     * @param Letra
     * @return Lista de entidades
     * @throws TransactionBaseException Caso algum erro ocorra.
     */
    @SuppressWarnings("unchecked")
    public List<Projeto> listarProUsuarioELetra(Login login, List<String> letras) throws TransactionBaseException {
        try {
            Disjunction disjunction = Restrictions.disjunction();
            for (String letra : letras) {
                disjunction.add(Restrictions.like("titulo.nome", letra, MatchMode.START));
            }
            Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass());
            criteria.createAlias("media", "media")
                    .createAlias("media.titulos", "titulo")
                    .add(disjunction)
                    .add(Restrictions.eq("usuario", login))
                    .add(Restrictions.eq("titulo.principal", true));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (HibernateException hexp) {
            throw new TransactionBaseException(hexp);
        }
    }

    /**
     * Obtem projeto pelo login e pela media
     *
     * @param login
     * @param media
     * @return Entidade
     * @throws TransactionBaseException Caso algum erro ocorra.
     */
    public Projeto findByCredencialEMedia(Login login, Media media) throws TransactionBaseException {
        return super.obterPorCriteriaUnique(Restrictions.eq("usuario", login), Restrictions.eq("media", media));
    }

    /**
     * obtem todos os projetos por usuario que estejao incompletos
     *
     * @param size
     * @param login
     * @param isNaoAssistidos
     * @return
     */
    public List<Projeto> findProjetosIncompletos(int size, Login login, boolean isNaoAssistidos) {
        try {
            Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass());
            criteria.add(Restrictions.eq("usuario", login))
                    .add(Restrictions.eq("status", Status.INCOMPLETO))
                    .createAlias("conteudosProjeto", "conteudosProjeto")
                    .createAlias("media", "media")
                    .add(Restrictions.eq("conteudosProjeto.baixado", true))
                    .add(Restrictions.eq("conteudosProjeto.assistido", isNaoAssistidos));
            criteria.addOrder(Order.asc("media.nomePrincipal"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(size);
            return criteria.list();
        } catch (Exception exp) {
            return null;
        }
    }
}
