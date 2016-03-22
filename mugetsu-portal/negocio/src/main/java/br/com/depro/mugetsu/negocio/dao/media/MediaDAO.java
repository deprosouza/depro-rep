package br.com.depro.mugetsu.negocio.dao.media;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.depro.fw.typezero.infrastructure.dao.TypezeroGenericJPADAO;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Incial - 29.06.2012
 */
@Service
public class MediaDAO extends TypezeroGenericJPADAO<Media> {

    /**
     * Otbtem entidade de media pelo nome da imagem de exbicao
     *
     * @param nomeImagem
     * @return Entidade Media
     * @throws TransactionBaseException Caso algum erro ocorra.
     */
    public Media obterPorNomeImagem(String nomeImagem) throws TransactionBaseException {
        return super.obterPorCriteriaUnique(Restrictions.eq("pathImagem", nomeImagem));
    }

    /**
     * Lista todas as media por letra e formato de media
     *
     * @param letras
     * @param isConsultaImagem
     * @param formatoMedia
     * @return Lista de Entidades
     * @throws TransactionBaseException Caso algum erro ocorra.
     */
    @SuppressWarnings("unchecked")
    public List<Media> listarMediaPorNomeEFormatoMedia(List<String> letras, boolean isConsultaImagem, FormatoMedia formatoMedia)
            throws TransactionBaseException {
        try {
            Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass()).setFetchMode("genero", FetchMode.JOIN);
            
            if (!isConsultaImagem) {
            	criteria.add(Restrictions.eq("formatoMedia", formatoMedia));
	            criteria.createAlias("titulos", "titulo");
	            criteria.add(Restrictions.eq("titulo.principal", Boolean.TRUE));
	            Disjunction disjunction = Restrictions.disjunction();
	            for (String letra : letras) {
	                disjunction.add(Restrictions.like("titulo.nome", letra, MatchMode.START));
	            }
	            criteria.add(disjunction);
            } else if (isConsultaImagem) {
            	String palavra = CollectionUtils.isEmpty(letras) ? "" : letras.get(0);
            	criteria.add(Restrictions.like("pathImagem", palavra, MatchMode.ANYWHERE));
            }
            
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (Exception exp) {
            throw new TransactionBaseException(exp);
        }
    }

    /**
     * Obtém lista de titulos pela Primeira letra
     *
     * @param letras
     * @param isConsultaImagem
     * @return Lista de Entidades
     * @throws TransactionBaseException Caso algum erro ocorra
     */
    @SuppressWarnings("unchecked")
    public List<Media> listarMediaPorPrimeiraLetra(List<String> letras, boolean isConsultaImagem) throws TransactionBaseException {
        try {
            Criteria criteria = super.getSession().createCriteria(super.getPerssitentClass()).setFetchMode("genero", FetchMode.JOIN);
            
            if (!isConsultaImagem) {
	            criteria.createAlias("titulos", "titulo");
	            criteria.add(Restrictions.eq("titulo.principal", Boolean.TRUE));
	            Disjunction disjunction = Restrictions.disjunction();
	            for (String letra : letras) {
	                disjunction.add(Restrictions.like("titulo.nome", letra, MatchMode.START));
	            }
	            criteria.add(disjunction);
            } else if (isConsultaImagem) {
            	String palavra = CollectionUtils.isEmpty(letras) ? "" : letras.get(0);
            	criteria.add(Restrictions.like("pathImagem", palavra, MatchMode.END));
            }
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (Exception exp) {
            throw new TransactionBaseException(exp);
        }
    }
}
