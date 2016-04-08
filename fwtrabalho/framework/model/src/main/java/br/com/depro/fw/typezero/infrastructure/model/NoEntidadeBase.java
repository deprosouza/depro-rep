package br.com.depro.fw.typezero.infrastructure.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * @author rsouza
 * @version 1.0 - versao inciao - 28.10.2015
 */
public class NoEntidadeBase {

	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
