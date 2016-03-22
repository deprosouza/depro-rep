package br.com.depro.mugetsu.model.media.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 27.07.2012
 */
public enum FormatoDorama {

	KDRAMA, JDRAMA, CDRAMA, TDRAMA, SGDRAMA, HKDRAMA;

	private static ResourceBundle properties = ResourceBundle.getBundle("i18n", new Locale("pt", "BR"));

	public String getDescricao() {
		return properties.getString(this.toString());
	}
	
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

	/**
	 * Retorna valores
	 * 
	 * @return
	 */
	public static List<FormatoDorama> getListValues() {
		return Arrays.asList(FormatoDorama.values());
	}
}
