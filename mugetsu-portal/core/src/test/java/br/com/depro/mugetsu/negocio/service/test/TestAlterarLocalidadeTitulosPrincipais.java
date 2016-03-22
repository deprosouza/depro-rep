//package br.com.depro.mugetsu.negocio.service.test;
//import java.util.List;
//
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.gekkostate.model.media.MediaTitulo;
//import br.com.depro.mugetsu.negocio.service.media.MediaTituloService;
//
///**
// * 
// * @author rsouza
// * @version 1.0 - Versao Inicial - 21.07.2012
// */
//public class TestAlterarLocalidadeTitulosPrincipais extends BaseTest {
//
//	@TypezeroBean
//	private MediaTituloService service;
//	
//	public void basicTest() throws NegocioException {
//		List<MediaTitulo> titulos = service.listarTodasEntidades();
//		for (MediaTitulo mediaTitulo : titulos) {
//			if (mediaTitulo.isPrincipal()) {
//				mediaTitulo.setLocalidade(null);
//				service.atualizar(mediaTitulo);
//			}
//		}
//	}
//	
//}
