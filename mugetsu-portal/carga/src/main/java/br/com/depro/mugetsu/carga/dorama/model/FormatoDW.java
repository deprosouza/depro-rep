package br.com.depro.mugetsu.carga.dorama.model;

import br.com.depro.mugetsu.model.media.vo.FormatoDorama;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10/02/2013
 */
public enum FormatoDW {

    KDrama(FormatoDorama.KDRAMA),
    JDrama(FormatoDorama.JDRAMA),
    TWDrama(FormatoDorama.TDRAMA),
    SGDrama(FormatoDorama.SGDRAMA),
    CDrama(FormatoDorama.CDRAMA),
    HKDrama(FormatoDorama.HKDRAMA);
    
    private FormatoDorama foramto;
    
    public static FormatoDW getEnum(FormatoDorama fd) {
    	FormatoDW formatoDW = null;
    	for (FormatoDW fdw : FormatoDW.values()) {
    		if (fdw.getForamto().equals(fd)) {
    			formatoDW = fdw;
    		}
    	}
    	return formatoDW;
    }

    private FormatoDW(FormatoDorama formato) {
        this.foramto = formato;
    }

    /**
     * @return the foramto
     */
    public FormatoDorama getForamto() {
        return foramto;
    }

    /**
     * @param foramto the foramto to set
     */
    public void setForamto(FormatoDorama foramto) {
        this.foramto = foramto;
    }
}
