package br.com.depro.mugetsu.web.utils;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 21.07.2012
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
