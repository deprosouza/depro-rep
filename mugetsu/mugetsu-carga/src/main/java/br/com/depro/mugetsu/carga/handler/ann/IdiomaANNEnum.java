package br.com.depro.mugetsu.carga.handler.ann;

import br.com.depro.mugetsu.model.LocaleEnum;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 09/10/2013
 */
public enum IdiomaANNEnum {

	NONE				(null),
	Arabic 				(LocaleEnum.AR_SA),
	Chinese 			(LocaleEnum.ZH_HK),
	ChineseTaiwan 		(LocaleEnum.ZH_TW),
	French 				(LocaleEnum.FR_FR),
	German 				(LocaleEnum.DE_DE),
	Italian				(LocaleEnum.IT_IT),
	Japanese 			(LocaleEnum.JA_JP),
	chineseJapanese		(LocaleEnum.JA_JP),
	Korean 				(LocaleEnum.KO_KR),
	Polish 				(LocaleEnum.PL_PL),
	BrazilPortuguese	(LocaleEnum.PT_BR),
	Portuguese			(LocaleEnum.PT_BR),
	Russian 			(LocaleEnum.RU_RU),
	Spanish 			(LocaleEnum.ES_ES),
	SpainSpanish		(LocaleEnum.ES_ES),
	Tagalog				(LocaleEnum.TL_PH),
	Taiwan 				(LocaleEnum.ZH_TW),
	ChineseHongKong		(LocaleEnum.ZH_HK),
	Swedish				(LocaleEnum.SV_SE),
	ChinesePRC			(LocaleEnum.ZH_HK),
	Vietnamese			(LocaleEnum.VI_VN),
	ViệtNam				(LocaleEnum.VI_VN),
	vietnamese			(LocaleEnum.VI_VN),
	Dutch				(LocaleEnum.NL_NL),
	FarsiJapanese		(LocaleEnum.JA_JP_ROMAJI),
	Farsi				(LocaleEnum.FA_IR),
	English				(LocaleEnum.EN_US),
	Danish				(LocaleEnum.DA_DK),
	Catalan				(LocaleEnum.CA_ES),
	Romanian			(LocaleEnum.RO_RO),
	Finnish				(LocaleEnum.FI_FI),
	Indonesian			(LocaleEnum.ID_ID),
	; 
	
	private LocaleEnum locale;
	
	public static IdiomaANNEnum getEnum(String strEnum) {
		IdiomaANNEnum retorno = NONE;
		for (IdiomaANNEnum item : IdiomaANNEnum.values()) {
			if (item.name().equalsIgnoreCase(strEnum)) {
				retorno = item;
			}
		}
		return retorno;
	}
	
	private IdiomaANNEnum(LocaleEnum locale) {
		this.locale = locale;
	}

	public LocaleEnum getLocale() {
		return locale;
	}

}
