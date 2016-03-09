package br.com.depro.ffsniffer.carga.linkshell.model;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.02.2015
 */
public enum QueryCombinationEnum {

	NUMBER_0("0", true),
	NUMBER_1("1", true),
	NUMBER_2("2", true),
	NUMBER_3("3", true),
	NUMBER_4("4", true),
	NUMBER_5("5", true),
	NUMBER_6("6", true),
	NUMBER_7("7", true),
	NUMBER_8("8", true),
	NUMBER_9("9", true),
	A("A", false),
	B("B", true),
	C("C", false),
	D("D", false),
	E("E", false),
	F("F", false),
	G("G", false),
	H("H", false),
	I("I", false),
	J("J", false),
	K("K", false),
	L("L", false),
	M("M", false),
	N("N", false),
	O("O", false),
	P("P", false),
	Q("Q", false),
	R("R", false),
	S("S", false),
	T("T", false),
	U("U", false),
	V("V", false),
	X("X", false),
	Y("Y", false),
	W("W", false),
	Z("Z", false),
	;
	
	private String extenso;
	private boolean solo;
	
	private QueryCombinationEnum(String extenso, boolean solo) {
		this.extenso = extenso;
		this.solo = solo;
	}

	/**
	 * @return the extenso
	 */
	public String getExtenso() {
		return extenso;
	}

	/**
	 * @return the solo
	 */
	public boolean isSolo() {
		return solo;
	}
	
}
