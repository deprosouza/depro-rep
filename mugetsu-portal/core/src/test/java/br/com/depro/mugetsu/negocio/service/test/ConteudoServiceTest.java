//package br.com.depro.mugetsu.negocio.service.test;
//
//import org.junit.Test;
//
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.gekkostate.model.media.Conteudo;
//import br.com.depro.gekkostate.model.media.Media;
//import br.com.depro.mugetsu.negocio.service.media.MediaService;
//
///**
// * 
// * @author rsouza
// * @version - Versao Inicial - 09.09.2012
// */
//public class ConteudoServiceTest extends BaseTest {
//
//	@TypezeroBean
//	private MediaService mediaService;
//	
//	@Test
//	public void criarConteudos() throws NegocioException {
//		mediaService = (MediaService) TypezeroSpringUtils.getBean(MediaService.class);
//		Media media = mediaService.obterPorId(6804L);
//		int quantidade = 280;
//		for (int i = 1 ; i <= quantidade ; i++) {
//			Conteudo conteudo = new Conteudo();
//			conteudo.setNumeroEpisodio(i);
//			conteudo.setMedia(media);
//			media.getConteudos().add(conteudo);
//		}
//		
//		this.mediaService.atualizar(media);
//	}
//}
