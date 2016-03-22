package br.com.depro.mugetsu.carga.dorama.model;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11/02/2013
 */
public enum IdiomaDW {

    Cantonese 					("language.zh.hk"),
    Cantonesed					("language.zh.hk"),
    Mandarin  					("language.zh.hk"),
    Vietnamese					("language.vi.vn"),
    English 					("language.en.us"),
    Cantonese_title_Mandarin    ("language.zh.hk"),
    Mandarin_title_Cantonese    ("language.zh.hk"),
    Chinese						("language.zh.hk"),
    Cantonese_Mandarin          ("language.zh.hk"),
    Mandarin_Cantonese          ("language.zh.hk"),
    Korean 						("language.kr.kr"),
    Cantonese___Mandarin        ("language.zh.hk"),
    Japanese		 			("language.ja.jp"),
    German						("language.de.de"),
    Official_international      ("language.en.us"),
    Other						(null),	
    Previous					(null),
    Alternative                 ("language.en.us"),
    en							("language.en.us"),
    ;
	
    private String prefixoIdoma;

    /**
     * @param prefixoIdoma
     */
    private IdiomaDW(String prefixoIdoma) {
        this.prefixoIdoma = prefixoIdoma;
    }

    /**
     * @return the prefixoIdoma
     */
    public String getPrefixoIdoma() {
        return prefixoIdoma;
    }

    /**
     * @param prefixoIdoma the prefixoIdoma to set
     */
    public void setPrefixoIdoma(String prefixoIdoma) {
        this.prefixoIdoma = prefixoIdoma;
    }
}
