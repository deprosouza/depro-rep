//package br.com.depro.mugetsu.negocio.service.test;
//import org.junit.Test;
//import org.springframework.stereotype.Service;
//
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.gekkostate.model.cenum.media.FormatoMedia;
//import br.com.depro.gekkostate.model.media.Media;
//import br.com.depro.mugetsu.negocio.service.media.MediaService;
//
///**
// * 
// * @author rsouza
// * @version 1.0 - Versao Inicial - 20.07.2012
// */
//@Service
//public class MediaServiceTest extends BaseTest {
//
//	@TypezeroBean
//	private MediaService service;
//	
//	@Test
//	public void testListarPorLetraEFormato() throws NegocioException {
//		int count = 0;
//		service = (MediaService) TypezeroSpringUtils.getBean(MediaService.class);
//		for (Media media : service.listarMediasPorPrimeiraLetraEFormatoMedia("S", FormatoMedia.ANIME)) {
//			System.out.println(media.getTituloPrincipal().getNome());
//			count++;
//		}
//		System.out.println(count);
//	}
//	
////	@Test
////	public void inserirNomePrincipalMedia() {
////		List<Media> medias = null;
////		medias = service.listarTodasEntidades();
////		for (Media media : medias) {
////			if (StringUtils.isBlank(media.getNomePrincipal())) {
////				media.setNomePrincipal(media.getTituloPrincipal().getNome());
////				try {
////					service.atualizar(media);
////				} catch (NegocioException e) {
////					System.out.println(e.getMessage());
////				}
////			}
////		}
////	}
//}
//
