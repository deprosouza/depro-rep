package br.com.depro.fw.typezero.infrastructure.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.depro.fw.typezero.infrastructure.model.audit.Auditoria.EventAudit;

/**
 * @author rsouza
 * @version 1.0 - versao iniciao - 26.10.2015
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Audit {

	EventAudit eventAudit();
	
}
