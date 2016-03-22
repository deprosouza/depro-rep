package br.com.depro.mugetsu.model.media.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 19.07.2012
 */
public enum FormatoAnime {

	TV, OAV, ONA, ESPECIAL;

	private static ResourceBundle properties = ResourceBundle.getBundle("i18n", new Locale("pt", "BR"));

	/**
	 * @return the prefixo
	 */
	public String getDescricao() {
		return properties.getString(this.toString());
	}
	
	public static FormatoAnime getEnum(String name) {
		FormatoAnime enumm = null;
		for (FormatoAnime item : FormatoAnime.values()) {
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
	public static List<FormatoAnime> getListValues() {
		return Arrays.asList(FormatoAnime.values());
	}
}
