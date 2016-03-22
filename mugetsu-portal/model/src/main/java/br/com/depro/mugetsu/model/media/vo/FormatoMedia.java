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
public enum FormatoMedia {

	ANIME, MANGA, SERIE, FILME, DORAMA, TOKUSATSU;

	private static ResourceBundle properties = ResourceBundle.getBundle("i18n", new Locale("pt", "BR"));

	public String getDescricao() {
		return properties.getString(this.toString());
	}
	
	public static FormatoMedia getEnum(String name) {
		FormatoMedia enumm = null;
		for (FormatoMedia item : FormatoMedia.values()) {
			if (item.name().equalsIgnoreCase(name)) {
				enumm = item;
				break;
			}
		}
		return enumm;
	}

	/**
	 * Indica se formato e anime
	 *
	 * @return true caso seja
	 */
	public boolean isAnime() {
		return this.equals(FormatoMedia.ANIME);
	}

	/**
	 * Indica se formato e manga
	 *
	 * @return true caso seja
	 */
	public boolean isManga() {
		return this.equals(FormatoMedia.MANGA);
	}

	/**
	 * Indica se formato e serie
	 *
	 * @return true caso seja
	 */
	public boolean isSerie() {
		return this.equals(FormatoMedia.SERIE);
	}

	/**
	 * Indica se formato e anime
	 *
	 * @return true caso seja
	 */
	public boolean isFilme() {
		return this.equals(FormatoMedia.FILME);
	}

	/**
	 * Indica se formato e dorama
	 * 
	 * @return true caso seja
	 */
	public boolean isDorama() {
		return this.equals(FormatoMedia.DORAMA);
	}

	/**
	 * Retorna valores
	 *
	 * @return
	 */
	public static List<FormatoMedia> getListValues() {
		return Arrays.asList(FormatoMedia.values());
	}
}
