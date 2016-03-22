package br.com.depro.mugetsu.core.service.media.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.dao.media.ConteudoDAO;
import br.com.depro.mugetsu.core.service.media.ConteudoService;
import br.com.depro.mugetsu.model.media.Episodio;
import br.com.depro.mugetsu.model.media.Media;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.07.2012
 */
@Service
public class ConteudoServiceImpl extends TypezeroGenericServiceImpl<Episodio, ConteudoDAO> implements ConteudoService {

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
        switch (media.getFormatoMedia()) {
        	case ANIME:
        		quantidade = media.getQuantidedaEpisodios();
        		break;
        	case MANGA:
        		quantidade = media.getQuantidadeVolumes();
        		break;
        	case FILME:
        		quantidade = 1;
        		break;
        }
        List<Episodio> conteudos = new ArrayList<Episodio>();
        for (int i = 1; i <= quantidade; i++) {
            Episodio conteudo = new Episodio();
            conteudo.setNumero(i);
            conteudos.add(conteudo);
        }
        media.setEpisodios(conteudos);
    }

    /**
     * @see ConteudoService#listarConteudoPorMediaId(Long)
     */
    public List<Episodio> listarConteudoPorMediaId(Long id) throws ApplicationException {
        try {
            return super.getDAO().obterPorMediaId(id);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see ConteudoService#listarConteudoPorMedia(Media)
     */
    public List<Episodio> listarConteudoPorMedia(Media media)
            throws ApplicationException {
        try {
            return super.getDAO().obterPorMedia(media);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }
}
