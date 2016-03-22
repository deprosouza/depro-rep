package br.com.depro.mugetsu.carga.dorama.model;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11/02/2013
 */
public enum DatasDW {

    Jan("01"),
    Feb("02"),
    Mar("03"),
    Apr("04"),
    May("05"),
    Jun("06"),
    Jul("07"),
    Aug("08"),
    Sep("09"),
    Oct("10"),
    Nov("11"),
    Dec("12");
    private String nunero;

    /**
     * @param nunero
     */
    private DatasDW(String nunero) {
        this.nunero = nunero;
    }

    /**
     * @return the nunero
     */
    public String getNunero() {
        return nunero;
    }

    /**
     * @param nunero the nunero to set
     */
    public void setNunero(String nunero) {
        this.nunero = nunero;
    }
}
