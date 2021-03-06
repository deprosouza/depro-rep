package br.com.depro.mugetsu.model.media.util;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
public enum FormatoAnime {

	TV,
	OAV,
	ONA,
	ESPECIAL,
	;

	public static FormatoAnime getEnum(String name) {
		FormatoAnime formatoRetorno = null;
		for (FormatoAnime formato : FormatoAnime.values()) {
			if (formato.name().equalsIgnoreCase(name)) {
				formatoRetorno = formato;
			}
		}
		return formatoRetorno;
	}
}
