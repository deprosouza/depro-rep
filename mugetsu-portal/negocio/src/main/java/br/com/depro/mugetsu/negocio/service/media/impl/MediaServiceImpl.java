package br.com.depro.mugetsu.negocio.service.media.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.exception.TransactionBaseException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.model.cenum.Alfabeto;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.negocio.dao.media.MediaDAO;
import br.com.depro.mugetsu.negocio.service.media.ConteudoService;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.negocio.service.media.MediaTituloService;

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

    /**
     * @see MediaService#inseirMedia(Media)
     */
    public Media salvar(Media media) throws ApplicationException {
    	Media mediaSalva = null;
        if (this.mediaTituloService.validarMediaTitulos(media)) {
            this.conteudoService.inserirConteudoMedia(media);
            mediaSalva = super.getDAO().salvar(media);
            gerarXML(mediaSalva);
        }
        return mediaSalva;
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
     * @see MediaService#obterMediaPorNomeImagem(String)
     */
    public Media obterMediaPorNomeImagem(String nomeImagem)
            throws ApplicationException {
        try {
            return super.getDAO().obterPorNomeImagem(nomeImagem);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see MediaService#listarMediasPorPrimeiraLetraEFormatoMedia(String,
     * boolean, FormatoMedia)
     */
    public List<Media> listarMediasPorPrimeiraLetraEFormatoMedia(String letra, boolean isConsultaImagem, FormatoMedia formatoMedia) 
    		throws ApplicationException {
        try {
        	List<String> paralavrasChaves = isConsultaImagem ? Arrays.asList(letra) : obterSequenciaLetras(letra);
            return super.getDAO().listarMediaPorNomeEFormatoMedia(paralavrasChaves, isConsultaImagem, formatoMedia);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
        }
    }

    /**
     * @see MediaService#listarMediasPorPrimeiraLetra(String, boolean)
     */
    public List<Media> listarMediasPorPrimeiraLetra(String letra, boolean isConsultaImagem) throws ApplicationException {
        try {
        	List<String> paralavrasChaves = isConsultaImagem ? Arrays.asList(letra) : obterSequenciaLetras(letra);
            return super.getDAO().listarMediaPorPrimeiraLetra(paralavrasChaves, isConsultaImagem);
        } catch (TransactionBaseException texp) {
            throw new ApplicationException(texp);
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
    /* (non-Javadoc)
     * @see br.com.depro.mugetsu.negocio.service.media.MediaService#adicionarConteudoPorMedia(br.com.depro.mugetsu.model.media.Media, java.lang.Integer)
     */
    public void inserirConteudoPorMedia(Media media, Integer quantidade)
            throws ApplicationException {
        int qtdeCadastrados = 0;
        if (FormatoMedia.MANGA.equals(media.getFormatoMedia())) {
            qtdeCadastrados = media.getVolumes();
        } else if (!FormatoMedia.FILME.equals(media.getFormatoMedia())) {
            qtdeCadastrados = media.getEpisodios();
        } else if (FormatoMedia.FILME.equals(media.getFormatoMedia())) {
            qtdeCadastrados = 1;
        }

        int conteudoSize = media.getConteudos().size();
        if (conteudoSize + quantidade - 1 > qtdeCadastrados) {
            throw new ApplicationException("MG3451");
        } else {
            if (FormatoMedia.MANGA.equals(media.getFormatoMedia())) {
                media.setVolumes(++conteudoSize);
            } else if (!FormatoMedia.FILME.equals(media.getFormatoMedia())) {
                media.setEpisodios(++conteudoSize);
            }
            this.conteudoService.inserirConteudoMedia(media);
        }
        super.getDAO().atualizar(media);
    }

    /**
     * Gera xml da media para futura carga DMU
     *
     * @param media
     */
    private void gerarXML(Media media) {
        try {
            String diretorio = this.propConfig.get(PATH_OUTPUT_XML);
            File file = new File(diretorio
                    + media.getTituloPrincipal().getNome()
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
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Obtem sequencia de letras permitidas
     *
     * @param letra
     * @return Lista de letras
     */
    private List<String> obterSequenciaLetras(String letra) {
        List<String> letras = new ArrayList<String>();
        for (Alfabeto alfabeto : Alfabeto.values()) {
            if (alfabeto.getLabel().equalsIgnoreCase(letra)) {
                for (String s : alfabeto.getValores()) {
                    letras.add(s);
                }
                break;
            }
        }
        return letras;
    }
}
