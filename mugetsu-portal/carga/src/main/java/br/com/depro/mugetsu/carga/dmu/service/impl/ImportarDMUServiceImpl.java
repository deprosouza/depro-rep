package br.com.depro.mugetsu.carga.dmu.service.impl;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.carga.anime.service.ImportarANNService;
import br.com.depro.mugetsu.carga.dmu.service.ImportarDMUService;
import br.com.depro.mugetsu.carga.dorama.service.ImportarDWService;
import br.com.depro.mugetsu.core.service.media.MediaService;
import br.com.depro.mugetsu.core.service.media.TemaService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.Tag;
import br.com.depro.mugetsu.model.media.nome.MediaNome;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.08.2012
 */
@Service
public class ImportarDMUServiceImpl implements ImportarDMUService {

    @Autowired
    private MediaService mediaService;
    @Autowired
    private TemaService temaService;
    @Autowired
    private ImportarANNService annService;
    @Autowired
    private ImportarDWService dwService;
    private List<Tag> temas;

    public Media atualizarDadosMedia(Long id) throws ApplicationException {
    	Media mediaAtualizada = null;
    	Media media = mediaService.buscarPorId(id);
    	switch (media.getFormatoMedia()) {
    		case ANIME:
    			mediaAtualizada = annService.atualizarDadosMedia(media);
    			break;
    		case DORAMA:
    			mediaAtualizada = dwService.atualizarDadosMedia(media);
    			break;
    	}
    	return mediaAtualizada;
    }
    
    /**
     * @see ImportarDMUService#importarFromXML(String)
     */
    public void importarFromXML(String dir) throws ApplicationException {
//        JAXBContext context = null;
//        Unmarshaller unmarshaller = null;
//        temas = temaService.buscarTodas();
//        try {
//            context = JAXBContext.newInstance(Media.class);
//            unmarshaller = context.createUnmarshaller();
//        } catch (JAXBException e) {
//            System.out.println(e.getMessage());
//        }
//
//        File xmls[] = new File(dir).listFiles();
//        for (File xml : xmls) {
//            try {
//                Media media = (Media) unmarshaller.unmarshal(xml);
//                media.setId(null);
//                for (MediaTitulo titulo : media.getTitulos()) {
//                    titulo.setId(null);
//                }
//                Set<Tag> temasMedia = media.getTemas();
//                media.setTemas(new HashSet<Tag>());
//                for (Tag tema : temasMedia) {
//                    boolean find = false;
//                    for (Tag temaCad : temas) {
//                        if (temaCad.getPrefixo().equalsIgnoreCase(tema.getPrefixo())) {
//                            find = true;
//                            media.getTemas().add(temaCad);
//                            break;
//                        }
//                    }
//
//                    if (!find) {
//                        tema.setId(null);
//                        this.temaService.salvar(tema);
//                        temas.add(tema);
//                        media.getTemas().add(tema);
//                    }
//                }
//                mediaService.salvar(media);
//            } catch (JAXBException e) {
//                System.out.println(e.getMessage());
//            }
//        }

    }
}
