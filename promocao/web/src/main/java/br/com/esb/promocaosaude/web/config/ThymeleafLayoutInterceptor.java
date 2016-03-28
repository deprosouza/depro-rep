package br.com.esb.promocaosaude.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 05.11.2015
 *
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	public static final String DEFAULT_LAYOUT = "layouts/default";
	public static final String LOGIN_LAYOUT = "layouts/auth";
	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	public static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "uri";
	public static final String URL_RESOURCES_EXT = "URL_RESOURCES";
    
    @Autowired
    private PropConfig propConfig;
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }
        if (modelAndView.getModel().containsKey(UNAUTHORIZED)) {
        	modelAndView.setViewName(LOGIN_LAYOUT);
        } else {
        	modelAndView.setViewName(DEFAULT_LAYOUT);
        }
        modelAndView.addObject(URL_RESOURCES_EXT, propConfig.get("url.resources"));
        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
        modelAndView.addObject("URL", propConfig.get("url.base"));
    }
    
    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }
}
