package br.com.depro.ffsniffer.carga.linkshell.model;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.02.2015
 */
public enum CharacterCountEnum {

	ENTRE_1_10("1-10"),
	ENTRE_11_30("11-30"),
	ENTRE_31_50("31-50"),
	ACIMA_50("51");
	
	private String param;
	
	private CharacterCountEnum(String param) {
		this.param = param;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

}
