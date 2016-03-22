//package br.com.depro.mugetsu.negocio.service.test;
//import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
//import br.com.depro.fw.typezero.infrastructure.exception.NegocioException;
//import br.com.depro.fw.typezero.infrastructure.test.BaseTest;
//import br.com.depro.gekkostate.model.security.Permissao;
//import br.com.depro.mugetsu.negocio.service.security.PermissaoService;
//
///**
// * 
// * @author rsouza
// * @version 1.0 - Versao Inicial - 22.07.2012
// */
//public class TesteListaPermissaoPorRole  extends BaseTest{
//
//	@TypezeroBean
//	private PermissaoService service;
//	
//	
//	public void basicTest() throws NegocioException {
//		
//		for (Permissao permissao : service.obterPermissoesPorIdRole(1L)) {
//			System.out.println(permissao.getPrefixo());
//		}
//	}
//}
