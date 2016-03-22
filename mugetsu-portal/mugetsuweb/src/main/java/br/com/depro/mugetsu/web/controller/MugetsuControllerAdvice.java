package br.com.depro.mugetsu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

@ControllerAdvice
public class MugetsuControllerAdvice {

	@ExceptionHandler(value = ApplicationException.class)
	public ModelAndView defailtFwHandler(HttpServletRequest request, ApplicationException aexp) {
        ModelAndView mav = new ModelAndView("errors/application");
        return mav;	
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView defailtFwHandler() {
        ModelAndView mav = new ModelAndView("errors/application");
        return mav;	
	}
}
