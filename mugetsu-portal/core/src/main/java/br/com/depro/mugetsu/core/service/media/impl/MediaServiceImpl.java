package br.com.depro.mugetsu.core.service.media.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.core.dao.media.MediaDAO;
import br.com.depro.mugetsu.core.service.media.ConteudoService;
import br.com.depro.mugetsu.core.service.media.MediaService;
import br.com.depro.mugetsu.core.service.media.MediaTituloService;
import br.com.depro.mugetsu.core.util.MCriteria;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Incial - 29.06.2012
 * @version 2.0 - Adicionando servico generico - 15.07.2012
 */
@Service
public class MediaServiceImpl extends TypezeroGenericServiceImpl<Media, MediaDAO> implements MediaService {

    private static final String PATH_OUTPUT_XML = "dir.path.output.carga.xml";
    @Autowired
    private MediaTituloService mediaTituloService;
    @Autowired
    private ConteudoService conteudoService;
    @Autowired
    private PropConfig propConfig;

    @Autowired
    public void initDAO(MediaDAO dao) {
        super.setDAO(dao);
    }
    
    public List<Media> buscaPorAtributos(MCriteria mcriteria) {
    	return getDAO().findByAttrs(mcriteria);
    }

    /**
     * @see MediaService#inseirMedia(Media)
     */
    public Media salvar(Media media) throws ApplicationException {
        if (this.mediaTituloService.validarMediaTitulos(media)) {
            this.conteudoService.inserirConteudoMedia(media);
            super.getDAO().salvar(media);
            gerarXML(media);
        }
        return null;
    }

    /**
     * @see MediaService#inserirLote(List)
     */
    public void inserirLote(List<Media> medias) throws ApplicationException {
        for (Media media : medias) {
            this.salvar(media);
        }
    }

    /**
     * @see MediaService#adicionarConteudoPorMediaId(Long, Integer)
     */
    public void adicionarConteudoPorMediaId(Long id, Integer quantidade)
            throws ApplicationException {
        try {
            this.inserirConteudoPorMedia(super.getDAO().obterPorId(id), quantidade);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see MediaService#adicionarConteudoPorMedia(Media, Integer)
     */
    public void inserirConteudoPorMedia(Media media, Integer quantidade)
            throws ApplicationException {
        /*int qtdeCadastrados = 0;
        if (FormatoMedia.MANGA.equals(media.getFormatoMedia())) {
            qtdeCadastrados = media.getQuantidadeVolumes();
        } else if (!FormatoMedia.FILME.equals(media.getFormatoMedia())) {
            qtdeCadastrados = media.getEpisodios();
        } else if (FormatoMedia.FILME.equals(media.getFormatoMedia())) {
            qtdeCadastrados = 1;
        }

        int conteudoSize = media.getEpisodios().size();
        if (conteudoSize + quantidade - 1 > qtdeCadastrados) {
            throw new ApplicationException("MG3451");
        } else {
            if (FormatoMedia.MANGA.equals(media.getFormatoMedia())) {
                media.setQuantidadeVolumes(++conteudoSize);
            } else if (!FormatoMedia.FILME.equals(media.getFormatoMedia())) {
                media.setQuantidedaEpisodios(++conteudoSize);
            }
            this.conteudoService.inserirConteudoMedia(media);
        }
        super.getDAO().atualizar(media);*/
    }

    /**
     * Gera xml da media para futura carga DMU
     *
     * @param media
     */
    private void gerarXML(Media media) {
        try {
           /* String diretorio = this.propConfig.get(PATH_OUTPUT_XML);
            File file = new File(diretorio
                    + media.getNomePrincipal()
                    .replaceAll(" ", "_")
                    .replaceAll("/", "")
                    .replaceAll("\\.", "")
                    .replaceAll("%", "")
                    .replaceAll(":", "") + ".xml");
            if (!file.exists()) {
                JAXBContext context = JAXBContext.newInstance(Media.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(media, file);
            }*/
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
