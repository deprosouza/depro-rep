package br.com.depro.mugetsu.model.media.util;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
public enum FormatoMedia {

	ANIME,
	FILME,
	SERIE,
	MANGA,
	DORAMA,
	HQ,
	TOKUSATSU,
	LIVRO;
	
	public static FormatoMedia getEnum(String name) {
		FormatoMedia formatoMedia = null;
		for (FormatoMedia formato : FormatoMedia.values()) {
			if (formato.name().equalsIgnoreCase(name)) {
				formatoMedia = formato;
			}
		}
		return formatoMedia;
	}
}
