package br.com.depro.mugetsu.negocio.service.media.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.model.media.Conteudo;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.negocio.dao.media.ConteudoDAO;
import br.com.depro.mugetsu.negocio.service.media.ConteudoService;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.07.2012
 */
@Service
public class ConteudoServiceImpl extends TypezeroGenericServiceImpl<Conteudo, ConteudoDAO> implements ConteudoService {

    /**
     * @see
     * TypezeroGenericServiceImpl#initDAO(br.com.dsphere.mugetsu.web.infrastructure.dao.MugetsuGenericDAO)
     */
    @Autowired
    public void initDAO(ConteudoDAO dao) {
        super.setDAO(dao);
    }

    /**
     * @see ConteudoService#criarConteudoMedia(Media)
     */
    public void inserirConteudoMedia(Media media) throws ApplicationException {
        int quantidade = 0;
        String formatoMedia = media.getFormatoMedia().getPrefixo();
        if (formatoMedia.equals(FormatoMedia.MANGA)) {
            quantidade = media.getVolumes();
        } else if (!FormatoMedia.FILME.equals(formatoMedia)) {
            quantidade = media.getEpisodios();
        } else {
            quantidade = 1;
        }
        List<Conteudo> conteudos = new ArrayList<Conteudo>();
        for (int i = 1; i <= quantidade; i++) {
            Conteudo conteudo = new Conteudo();
            conteudo.setNumeroEpisodio(i);
            conteudo.setMedia(media);
            conteudos.add(conteudo);
        }
        media.setConteudos(conteudos);
    }

    /**
     * @see ConteudoService#listarConteudoPorMediaId(Long)
     */
    public List<Conteudo> listarConteudoPorMediaId(Long id) throws ApplicationException {
        try {
            return super.getDAO().obterPorMediaId(id);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see ConteudoService#listarConteudoPorMedia(Media)
     */
    public List<Conteudo> listarConteudoPorMedia(Media media)
            throws ApplicationException {
        try {
            return super.getDAO().obterPorMedia(media);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }
}
