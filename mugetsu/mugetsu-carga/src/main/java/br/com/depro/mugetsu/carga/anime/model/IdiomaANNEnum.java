package br.com.depro.mugetsu.carga.anime.model;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 09/10/2013
 */
public enum IdiomaANNEnum {

	NONE				(""),
	Arabic 				("language.ar.sa"),
	Chinese 			("language.zh.hk"),
	ChineseTaiwan 		("language.zh.tw"),
	French 				("language.fr.fr"),
	German 				("language.de.de"),
	Italian				("language.it.it"),
	Japanese 			("language.ja.jp"),
	chineseJapanese		("language.ja.jp"),
	Korean 				("language.ko.kr"),
	Polish 				("language.pl.pl"),
	BrazilPortuguese	("language.pt.br"),
	Portuguese			("language.pt.br"),
	Russian 			("language.ru.ru"),
	Spanish 			("language.es.es"),
	SpainSpanish		("language.es.es"),
	Tagalog				("language.tl.ph"),
	Taiwan 				("language.zh.tw"),
	ChineseHongKong		("language.zh.hk"),
	Swedish				("language.sv.se"),
	ChinesePRC			("language.zh.hk"),
	Vietnamese			("language.vi.vn"),
	ViệtNam				("language.vi.vn"),
	vietnamese			("language.vi.vn"),
	Dutch				("language.nl_nl"),
	FarsiJapanese		("language.ja.jp.romaji"),
	Farsi				("language.fa.ir"),
	English				("language.en.us"),
	Danish				("language.da.dk"),
	Catalan				("language.ca.es"),
	Romanian			("language.ro.ro"),
	Finnish				("language.fi_fi"),
	Indonesian			("language.id.id"),
	; 
	
	private String prefixo;
	
	public static IdiomaANNEnum getEnum(String strEnum) {
		IdiomaANNEnum retorno = NONE;
		for (IdiomaANNEnum item : IdiomaANNEnum.values()) {
			if (item.name().equalsIgnoreCase(strEnum)) {
				retorno = item;
			}
		}
		return retorno;
	}
	
	/**
	 * Contrutor default da classe
	 * @param prefixo
	 */
	private IdiomaANNEnum(String prefixo) {
		setPrefixo(prefixo);
	}

	/**
	 * @return the prefixo
	 */
	public String getPrefixo() {
		return prefixo;
	}

	/**
	 * @param prefixo the prefixo to set
	 */
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
}
