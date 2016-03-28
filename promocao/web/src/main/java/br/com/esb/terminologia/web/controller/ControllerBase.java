package br.com.esb.terminologia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import br.com.depro.fw.typezero.infrastructure.security.SessionBase;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.esb.common.ws.security.Workspace;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 05.11.2015
 */
public class ControllerBase {

	@Autowired
	@Qualifier("sessionManagerWEB")
	protected SessionBase<Workspace> sessionManager;
	@Autowired
	protected PropConfig propConfig;


	protected ModelAndView genereteModelFromSession(String uri) {
		return new ModelAndView(uri);
	}
	
	
	protected ModelAndView genereteModelWithoutSession(String uri, boolean isValidaSessao) {
		return new ModelAndView(uri);
	}
	
}
