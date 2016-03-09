package br.com.depro.ffsniffer.web.util;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public enum TipoEvento {

    EXIBICAO("dir.path.output.img.exibicao"),
    GALERIA("galeria"),
    AVATAR("dir.path.user");
    private String key;

    private TipoEvento(String key) {
        this.key = key;
    }

    /**
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }
}
