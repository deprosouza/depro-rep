package br.com.depro.mugetsu.model.cenum;

import java.util.Arrays;
import java.util.List;

import br.com.depro.mugetsu.model.media.vo.FormatoAnime;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.07.2012
 */
public enum Status {

	ATIVO ("STATUS.ATIVO"),
	INATIVO ("STATUS.INATIVO"),
	BLOQUEADO ("STATUS.BLOQUEADO"),
	EXPIRADO ("STATUS.EXPIRADO"),
	ECERRADO ("STATUS.ENCERRADO"),
	PENDENTE ("STATUS.PENDENTE"),
	COMPLETO ("STATUS.COMPLETO"),
	INCOMPLETO ("STATUS.INCOMPLETO"),
	OK ("STATUS.OK");
	
	private String prefixo;
	
	Status(String prefixo) {
		this.setPrefixo(prefixo);
	}

	/**
	 * @return the prefixo
	 */
	public String getPrefixo() {
		return prefixo;
	}

	/**
	 * 
	 * @param prefixo
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
