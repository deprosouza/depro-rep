package br.com.depro.fw.typezero.infrastructure.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author rsouza
 * @version 1.0 - versao inciao - 28.10.2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NoEntidadeBase implements Serializable {

	/** Numero serial UUID da classe */
	private static final long serialVersionUID = 3593902591577473262L;

	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
