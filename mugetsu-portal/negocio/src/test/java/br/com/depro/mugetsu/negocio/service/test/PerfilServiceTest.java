//package br.com.depro.mugetsu.negocio.service.test;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.commons.lang.StringUtils;
//import org.junit.Test;
//
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
//import br.com.depro.gekkostate.model.media.Genero;
//import br.com.depro.gekkostate.model.media.Media;
//import br.com.depro.gekkostate.model.security.Login;
//import br.com.depro.mugetsu.negocio.service.PerfilService;
//import br.com.depro.mugetsu.negocio.service.security.LoginService;
//
///**
// * 
// * @author rsouza
// * @version 1.0 - Versao Iniicial - 18.08.2012
// */
//public class PerfilServiceTest extends BaseTest {
//
//	private static final String PATH_PROPERTIES_WEB = "/home/rsouza/dsphere/workspace/mugetsu-web/WebContent/WEB-INF/i3-label.properties";
//	
//	@TypezeroBean
//	private LoginService loginService;
//	
//	@TypezeroBean
//	private PerfilService perfilService;
//	
//	private PropConfig propConfig;
//	
//	@Test
//	public void testListarIndicacoesUsuario() throws NegocioException, FileNotFoundException, IOException {
//		propConfig = new PropConfig(PATH_PROPERTIES_WEB);
//		Login login = loginService.obterPorId(1L);
//		List<Media> medias = perfilService.listraIndicacoesUsuario(login);
//		Collections.sort(medias);
//		for (Media media : medias) {
//			System.out.println(media.getTituloPrincipal().getNome() 
//					+ " - " + propConfig.get(media.getTipoMedia())
//					+ " - " + obterGenero(media.getGeneros()));
//		}
//	}
//	
//	/**
//	 * Obtem generos formatados
//	 * 
//	 * @param generos
//	 * @return
//	 */
//	private String obterGenero(Set<Genero> generos) { 
//		String retorno = "";
//		for (Genero genero : generos) {
//			if (StringUtils.isBlank(retorno)) {
//				retorno = propConfig.get(genero.getPrefixo());
//			} else {
//				retorno += ", " + propConfig.get(genero.getPrefixo());
//			}
//		}
//		return retorno;
//	}
//}
