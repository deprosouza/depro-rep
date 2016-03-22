package br.com.depro.mugetsu.negocio.dao.media;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.media.MediaTitulo;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Incial - 30.06.2012
 */
@Service
public class MediaTituloDAO extends TypezeroGenericJPADAO<MediaTitulo> {

    /**
     * Obtem entidade de {@link MediaTitulo} apartir do nome e formato media e
     * anime
     *
     * @param nome
     * @param formatoMedia
     * @param formatoAnime
     * @param formatoDorama
     * @return Retorna entidade {@link MediaTitulo} caso exista
     * @throws TransactionBaseException Caso algum erro ocorra
     */
    public MediaTitulo obterTituloPorNomeEFormatoMediaEFormatoAnime(String nome, FormatoMedia formatoMedia,
            FormatoAnime formatoAnime, FormatoDorama formatoDorama) throws TransactionBaseException {
        Session session = (Session) super.getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(super.getPerssitentClass());
        criteria.createAlias("media", "media")
                .add(Restrictions.eq("media.formatoMedia", formatoMedia))
                .add(Restrictions.eq("nome", nome));

        if (formatoAnime != null && formatoMedia.equals(FormatoMedia.ANIME)) {
            criteria.add(Restrictions.eq("media.formatoAnime", formatoAnime));
        } else if (formatoDorama != null && formatoMedia.equals(FormatoMedia.DORAMA)) {
            criteria.add(Restrictions.eq("media.formatoDorama", formatoDorama));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (MediaTitulo) criteria.uniqueResult();
    }
}
