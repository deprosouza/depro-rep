package br.com.depro.mugetsu.model.media.util;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 27.07.2012
 */
public enum FormatoDorama {

	KDRAMA, JDRAMA, CDRAMA, TDRAMA, SGDRAMA, HKDRAMA;

	public static FormatoDorama getEnum(String name) {
		FormatoDorama enumm = null;
		for (FormatoDorama item : FormatoDorama.values()) {
			if (item.name().equalsIgnoreCase(name)) {
				enumm = item;
				break;
			}
		}
		return enumm;
	}
}
