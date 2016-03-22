package br.com.depro.mugetsu.model.cenum;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.08.2012
 */
public enum Origem {

	DMU, ANN, DW,;
	
	private static ResourceBundle properties = ResourceBundle.getBundle("i18n", new Locale("pt", "BR"));

	public String getDescricao() {
		return properties.getString(this.toString());
	}
}
