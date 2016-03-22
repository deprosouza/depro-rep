package br.com.depro.mugetsu.model.media.vo;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 19/09/2012
 */
public enum GeneroEnum {

    ACAO 	("G.TYPE.ACAO"), 	
    ADULTO 	("G.TYPE.ADULTO"), 	
    AVENTURA 	("G.TYPE.AVENTURA"), 
    COMEDIA 	("G.TYPE.COMEDIA"), 	
    DRAMA	("G.TYPE.DRAMA"), 	
    DOC 	("G.TYPE.DOC"), 		
    ECCHI 	("G.TYPE.ECCHI"), 	
    FANTASIA 	("G.TYPE.FANTASIA"), 
    FICC 	("G.TYPE.FICC"), 	
    HEN 	("G.TYPE.HEN"), 		
    HIS 	("G.TYPE.HIS"), 		
    HOR 	("G.TYPE.HOR"), 		
    MAGIA 	("G.TYPE.MAGIA"), 	
    MECHA 	("G.TYPE.MECHA"), 	
    MILITAR 	("G.TYPE.MILITAR"), 	
    MISTERIO 	("G.TYPE.MISTERIO"), 
    MUSICA 	("G.TYPE.MUSICA"),	
    POLICE 	("G.TYPE.POLICE"), 	
    PSY		("G.TYPE.PSY"), 		
    ROMANCE 	("G.TYPE.ROMANCE"), 	
    SHOUJO 	("G.TYPE.SHOUJO"), 	
    SHOUJOAI 	("G.TYPE.SHOUJOAI"),	
    SHOUNEN 	("G.TYPE.SHOUNEN"), 	
    SHOUNENAI 	("G.TYPE.SHOUNENAI"),
    SLL 	("G.TYPE.SLL"), 		
    ESPORTE	("G.TYPE.ESPORTE"), 	
    SUPERN 	("G.TYPE.SUPERN"), 	
    TORNEIO 	("G.TYPE.TORNEIO"), 	
    YAOI 	("G.TYPE.YAOI"), 	
    YURI 	("G.TYPE.YURI");
	
    private String prefixo;

    GeneroEnum(String prefixo) {
        this.setPrefixo(prefixo);
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
