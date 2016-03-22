//package br.com.depro.mugetsu.negocio.service.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.stereotype.Service;
//
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.gekkostate.model.media.Genero;
//import br.com.depro.gekkostate.model.projeto.Projeto;
//import br.com.depro.gekkostate.model.security.Login;
//import br.com.depro.mugetsu.negocio.service.media.MediaService;
//import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
//import br.com.depro.mugetsu.negocio.service.security.LoginService;
//
//@Service
//public class ProjetoServiceTest extends BaseTest {
//
//	@TypezeroBean
//	private ProjetoService service;
//	
//	@TypezeroBean
//	private LoginService loginService;
//	
//	@TypezeroBean
//	private MediaService mediaService;
//
//	@Test
//	public void basicTest() throws NegocioException {
//		try {
//			Login login = loginService.obterPorId(1L);
//			
//			for (Projeto projeto : service.listarProjetosPorUsuarioEPrimeiraLetra(login, "H"))
//				System.out.println(projeto.getMedia().getTituloPrincipal().getNome());
//		} catch (NegocioException nexp) {
//			System.out.println("Projeto não existe!");
//		}
//	}
//
//	@Test
//	public void generoTest() throws NegocioException {
//		try {
//			List<Projeto> projetos = service.listarTodasEntidades();
//			for (Projeto dado : projetos) {
//				mediaService.refresh(dado.getMedia());
//				for (Genero genero : dado.getMedia().getGeneros()) {
//					System.out.println(dado.getMedia().getId() + " - " + genero.getId());
//				}
//			}
//		} catch (NegocioException nexp) {
//			nexp.printStackTrace(System.err);
//		}
//	}
//}
