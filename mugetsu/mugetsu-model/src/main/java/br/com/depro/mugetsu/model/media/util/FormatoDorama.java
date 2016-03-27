package br.com.depro.mugetsu.model.media.util;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 27.07.2012
 */
public enum FormatoDorama {

	KDRAMA, JDRAMA, CDRAMA, TDRAMA, SGDRAMA, HKDRAMA;

	public static FormatoDorama getEnum(String name) {
		FormatoDorama formatoRetorno = null;
		for (FormatoDorama formato : FormatoDorama.values()) {
			if (formato.name().equalsIgnoreCase(name)) {
				formatoRetorno = formato;
			}
		}
		return formatoRetorno;
	}
}
