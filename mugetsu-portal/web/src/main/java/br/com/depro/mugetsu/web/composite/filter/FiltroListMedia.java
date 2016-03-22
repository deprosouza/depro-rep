package br.com.depro.mugetsu.web.composite.filter;

import java.util.ArrayList;
import java.util.List;

import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.web.composite.ListMedia.Layout;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.08.2012
 */
public class FiltroListMedia {

	private String letra;
	private List<String> generos;
	private List<String> formatoMedias = new ArrayList<String>();
	private List<String> formatoAnimes = new ArrayList<String>();
	private List<String> formatoDoramas = new ArrayList<String>();
	private String palavra;
	private Layout layout;
	private boolean valid = false;
	private boolean isConsultaImagem = false;
	private FormatoMedia formatoSelecao;

	/**
	 * @return the letra
	 */
	public String getLetra() {
		return letra;
	}

	/**
	 * @param letra the letra to set
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}

	/**
	 * @return the generos
	 */
	public List<String> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	/**
	 * @return the formatoMedias
	 */
	public List<String> getFormatoMedias() {
		return formatoMedias;
	}

	/**
	 * @param formatoMedias the formatoMedias to set
	 */
	public void setFormatoMedias(List<String> formatoMedias) {
		this.formatoMedias = formatoMedias;
	}

	/**
	 * @return the formatoAnimes
	 */
	public List<String> getFormatoAnimes() {
		return formatoAnimes;
	}

	/**
	 * @param formatoAnimes the formatoAnimes to set
	 */
	public void setFormatoAnimes(List<String> formatoAnimes) {
		this.formatoAnimes = formatoAnimes;
	}

	/**
	 * @return the formatoDoramas
	 */
	public List<String> getFormatoDoramas() {
		return formatoDoramas;
	}

	/**
	 * @param formatoDoramas the formatoDoramas to set
	 */
	public void setFormatoDoramas(List<String> formatoDoramas) {
		this.formatoDoramas = formatoDoramas;
	}

	/**
	 * @return the palavra
	 */
	public String getPalavra() {
		return palavra;
	}

	/**
	 * @param palavra the palavra to set
	 */
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid && layout != null;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the isConsultaImagem
	 */
	public boolean isConsultaImagem() {
		return isConsultaImagem;
	}

	/**
	 * @param isConsultaImagem the isConsultaImagem to set
	 */
	public void setConsultaImagem(boolean isConsultaImagem) {
		this.isConsultaImagem = isConsultaImagem;
	}

	/**
	 * @return the formatoSelecao
	 */
	public FormatoMedia getFormatoSelecao() {
		return formatoSelecao;
	}

	/**
	 * @param formatoSelecao the formatoSelecao to set
	 */
	public void setFormatoSelecao(FormatoMedia formatoSelecao) {
		this.formatoSelecao = formatoSelecao;
	}
	
}
