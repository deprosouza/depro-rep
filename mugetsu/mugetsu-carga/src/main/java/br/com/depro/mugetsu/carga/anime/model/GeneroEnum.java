package br.com.depro.mugetsu.carga.anime.model;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 19/09/2012
 */
public enum GeneroEnum {

    ACAO 	    ("GENERO.ACAO"), 	
    ADULTO 	    ("GENERO.ADULTO"), 	
    AVENTURA 	("GENERO.AVENTURA"), 
    COMEDIA 	("GENERO.COMEDIA"), 	
    DRAMA	    ("GENERO.DRAMA"), 	
    DOC 	    ("GENERO.DOC"), 		
    ECCHI 	    ("GENERO.ECCHI"), 	
    FANTASIA 	("GENERO.FANTASIA"), 
    FICC 	    ("GENERO.FICC"), 	
    HEN 	    ("GENERO.HEN"), 		
    HIS 	    ("GENERO.HIS"), 		
    HOR 	    ("GENERO.HOR"), 		
    MAGIA 	    ("GENERO.MAGIA"), 	
    MECHA 	    ("GENERO.MECHA"), 	
    MILITAR 	("GENERO.MILITAR"), 	
    MISTERIO	("GENERO.MISTERIO"), 
    MUSICA 	    ("GENERO.MUSICA"),	
    POLICE 	    ("GENERO.POLICE"), 	
    PSY		    ("GENERO.PSY"), 		
    ROMANCE 	("GENERO.ROMANCE"), 	
    SHOUJO 	    ("GENERO.SHOUJO"), 	
    SHOUJOAI 	("GENERO.SHOUJOAI"),	
    SHOUNEN 	("GENERO.SHOUNEN"), 	
    SHOUNENAI 	("GENERO.SHOUNENAI"),
    SLL 	    ("GENERO.SLL"), 		
    ESPORTE	    ("GENERO.ESPORTE"), 	
    SUPERN 	    ("GENERO.SUPERN"), 	
    TORNEIO     ("GENERO.TORNEIO"), 	
    YAOI 	    ("GENERO.YAOI"), 	
    YURI 	    ("GENERO.YURI");
	
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
