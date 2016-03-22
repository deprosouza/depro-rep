package br.com.depro.mugetsu.model.cenum;

import java.util.Arrays;
import java.util.List;

import br.com.depro.mugetsu.model.media.vo.FormatoAnime;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 14.08.2012
 */
public enum CategoriaGrupo {

	LEEACHER ("GRUPO.TYPE.LEEACHER"),
	FANSUB ("GRUPO.TYPE.FANSUB"),
	AMBOS ("GRUPO.TYPE.AMBOS");
	
	private String prefixo;
	
	CategoriaGrupo(String prefixo) {
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
	
	/**
	 * Retorna valores
	 * 
	 * @return
	 */
	public static List<FormatoAnime> getListValues() {
		return Arrays.asList(FormatoAnime.values());
	}
}
