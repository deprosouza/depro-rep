package br.com.depro.fw.typezero.infrastructure.utils;

import br.com.depro.fw.typezero.infrastructure.bean.utils.MessageUtil;

/**
 * @author rsouza
 * @version 2.0 - Versao Inicial - 04.03.2016
 */
public enum FWCode {

    FW0000,
    FW0001,//TypezeroCriteria
    FW0002,//TypezeroCriteria
    FW0004,
    FW0005,
    FW0006,
    ;
	
	private MessageUtil messageUtil = new MessageUtil(FWCode.class);
	
	public String getDescricao() {
		return messageUtil.getMessage(this);
	}	
	
	public static FWCode getEnum(String name) {
		FWCode code = FW0000;
		for (FWCode item : FWCode.values()) {
			if (item.name().contains(name)) {
				code = item;
				break;
			}
		}
		return code;
	}
}
