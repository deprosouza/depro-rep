package br.com.depro.ffsniffer.web.util;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public enum PageURIEnum {
    
    HOME                                ("/"),
    
    CONSULTA_PLAYER                 	("/WEB-INF/pages/consulta/player.zul"),
    ;
    
    
    private String uri;
    
    /**
     * Construtor padrao da classe
     * @param uri 
     */
    private PageURIEnum(String uri) {
        this.uri = uri;
    }
    
    /**
     * Obtem enum apartir de uma string
     * @param enumn
     * @return 
     */
    public static PageURIEnum getEnum(String enumn) {
        PageURIEnum navigateEnum = HOME;
        for (PageURIEnum item : values()) {
            if (item.name().equals(enumn)) {
                navigateEnum = item;
                break;
            }
        }
        return navigateEnum;
    }

    /**
     *
     * @return
     */
    public String getUri() {
        return uri;
    }
}
