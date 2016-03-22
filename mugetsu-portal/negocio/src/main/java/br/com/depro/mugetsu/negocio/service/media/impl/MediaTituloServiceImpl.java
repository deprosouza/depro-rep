package br.com.depro.mugetsu.negocio.service.media.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.MediaTitulo;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.negocio.dao.media.MediaTituloDAO;
import br.com.depro.mugetsu.negocio.service.media.MediaTituloService;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versão Inicial - 30.06.2012
 */
@Service
public class MediaTituloServiceImpl extends TypezeroGenericServiceImpl<MediaTitulo, MediaTituloDAO>
        implements MediaTituloService {

    /**
     * @see TypezeroGenericServiceImpl#initDAO(br.com.depro.typezero.infrastructure.dao.DSphereGenericDAO)
     */
    @Autowired
    public void initDAO(MediaTituloDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see MediaTituloService#validarMediaTitulos(Media)
     */
    public boolean validarMediaTitulos(Media media) throws ApplicationException {
        try {
            boolean isValid = false;
            FormatoMedia fm = media.getFormatoMedia();
            FormatoAnime fa = media.getFormatoAnime();
            FormatoDorama fd = media.getFormatoDorama();
            for (Iterator<MediaTitulo> iterator = media.getTitulos().iterator(); iterator.hasNext();) {
                MediaTitulo titulo = iterator.next();
                if (!this.isNomeMediaExistente(titulo.getNome(), fm, fa, fd)) {
                    if (titulo.isPrincipal()) {
                        media.setNomePrincipal(titulo.getNome());
                    }
                    titulo.setMedia(media);
                    isValid = true;
                } else {
                    iterator.remove();
                }
            }
            return isValid;
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * Valida se media ja existe cadastra na base de dados
     * 
     * @param nome
     * @param formatoMedia
     * @param formatoAnime
     * @param formatoDorama
     * @return TRUE caso exista
     */
    private boolean isNomeMediaExistente(String nome, FormatoMedia formatoMedia,
            FormatoAnime formatoAnime, FormatoDorama formatoDorama) throws TransactionBaseException {
        boolean retorno = false;
        MediaTitulo titulo = super.getDAO().obterTituloPorNomeEFormatoMediaEFormatoAnime(nome, formatoMedia, formatoAnime, formatoDorama);
        if (titulo != null) {
            retorno = true;
        }
        return retorno;
    }
}
