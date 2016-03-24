package br.com.depro.mugetsu.carga.service;
//package br.com.depro.mugetsu.carga.ann.service;
//
//import java.io.File;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
//import br.com.depro.mugetsu.core.media.service.MediaService;
//import br.com.depro.mugetsu.model.media.Media;
//
///**
// * 
// * @author rsouza
// * @version 1.0 - Versao Inicial - 15.08.2012
// */
//@Service
//public class ImportacaoServiceImpl implements ImportacaoService {
//
//    @Autowired
//    private MediaService mediaService;
//    @Autowired
//    private TemaService temaService;
//    private List<Tema> temas;
//
//    /**
//     * @see ImportacaoService#importarFromXML(String)
//     */
//    public void importarFromXML(String dir) throws ApplicationException {
//        JAXBContext context = null;
//        Unmarshaller unmarshaller = null;
//        temas = temaService.buscaTodos();
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
//                System.out.println(xml.getName());
//                Media media = (Media) unmarshaller.unmarshal(xml);
//                media.setId(null);
//                for (MediaTitulo titulo : media.getTitulos()) {
//                    titulo.setId(null);
//                }
//                Set<Tema> temasMedia = media.getTemas();
//                media.setTemas(new HashSet<Tema>());
//                for (Tema tema : temasMedia) {
//                    boolean find = false;
//                    for (Tema temaCad : temas) {
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
//
//    }
//}
