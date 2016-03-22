//package br.com.depro.mugetsu.negocio.service.test;
//
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;
//import br.com.depro.gekkostate.model.media.MediaTitulo;
//import br.com.depro.mugetsu.negocio.service.media.MediaTituloService;
//
//public class MediaTituloServiceTest {
//
//	public static void main(String[] args) throws NegocioException {
//		MediaTituloService service = (MediaTituloService) TypezeroSpringUtils.getBean(MediaTituloService.class);
//		MediaTitulo titulo = new MediaTitulo();
//		titulo.setNome("Teste Rafael");
//		titulo.setPrincipal(true);
//		
//		service.inserir(titulo);
//		
//		for (MediaTitulo item : service.listarTodasEntidades()) {
//			System.out.println(item.getNome());
//		}
//	}
//}
