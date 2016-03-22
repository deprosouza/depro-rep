package br.com.depro.mugetsu.model.media;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 06/05/2012
 */
public class MediaConfiguration implements InitializingBean {

	private boolean campoQuantidadeEpisodios = false;
	private boolean campoQuantidadeCapitulos = false;
	private boolean campoQuantidadeVolumes = false;
	private boolean campoQuantidadeTemporadas = false;
	private boolean campoDuracao = false;
	private boolean campoFormatoAnime = false;
	private boolean campoForamtoDorama = false;
	private boolean campoBroadcast = false;
	private boolean campoAno = false;
	private boolean campoGenero = false;
	private Map<String, Boolean> mapGenerosPermitidos;

	/**
	 * @see InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.mapGenerosPermitidos, "Generos permitidos deve ser especificado");
	}
	
	/**
	 * Retorna se o genero esta disponivel para o formato atual da midia
	 * 
	 * @param prefixo
	 * @return {@link Boolean}
	 */
	public boolean isGeneroHabilitado(String prefixo) {
		return this.mapGenerosPermitidos.get(prefixo);
	}
	
	/**
	 * @return the campoQuantidadeEpisodios
	 */
	public boolean isCampoQuantidadeEpisodios() {
		return campoQuantidadeEpisodios;
	}

	/**
	 * @param campoQuantidadeEpisodios the campoQuantidadeEpisodios to set
	 */
	public void setCampoQuantidadeEpisodios(boolean campoQuantidadeEpisodios) {
		this.campoQuantidadeEpisodios = campoQuantidadeEpisodios;
	}

	/**
	 * @return the campoQuantidadeCapitulos
	 */
	public boolean isCampoQuantidadeCapitulos() {
		return campoQuantidadeCapitulos;
	}

	/**
	 * @param campoQuantidadeCapitulos the campoQuantidadeCapitulos to set
	 */
	public void setCampoQuantidadeCapitulos(boolean campoQuantidadeCapitulos) {
		this.campoQuantidadeCapitulos = campoQuantidadeCapitulos;
	}

	/**
	 * @return the campoQuantidadeVolumes
	 */
	public boolean isCampoQuantidadeVolumes() {
		return campoQuantidadeVolumes;
	}

	/**
	 * @param campoQuantidadeVolumes the campoQuantidadeVolumes to set
	 */
	public void setCampoQuantidadeVolumes(boolean campoQuantidadeVolumes) {
		this.campoQuantidadeVolumes = campoQuantidadeVolumes;
	}

	/**
	 * @return the campoQuantidadeTemporadas
	 */
	public boolean isCampoQuantidadeTemporadas() {
		return campoQuantidadeTemporadas;
	}

	/**
	 * @param campoQuantidadeTemporadas the campoQuantidadeTemporadas to set
	 */
	public void setCampoQuantidadeTemporadas(boolean campoQuantidadeTemporadas) {
		this.campoQuantidadeTemporadas = campoQuantidadeTemporadas;
	}

	/**
	 * @return the campoDuracao
	 */
	public boolean isCampoDuracao() {
		return campoDuracao;
	}

	/**
	 * @param campoDuracao the campoDuracao to set
	 */
	public void setCampoDuracao(boolean campoDuracao) {
		this.campoDuracao = campoDuracao;
	}

	/**
	 * @return the campoFormatoAnime
	 */
	public boolean isCampoFormatoAnime() {
		return campoFormatoAnime;
	}

	/**
	 * @param campoFormatoAnime the campoFormatoAnime to set
	 */
	public void setCampoFormatoAnime(boolean campoFormatoAnime) {
		this.campoFormatoAnime = campoFormatoAnime;
	}

	/**
	 * @return the campoForamtoDorama
	 */
	public boolean isCampoForamtoDorama() {
		return campoForamtoDorama;
	}

	/**
	 * @param campoForamtoDorama the campoForamtoDorama to set
	 */
	public void setCampoForamtoDorama(boolean campoForamtoDorama) {
		this.campoForamtoDorama = campoForamtoDorama;
	}

	/**
	 * @return the campoBroadcast
	 */
	public boolean isCampoBroadcast() {
		return campoBroadcast;
	}

	/**
	 * @param campoBroadcast the campoBroadcast to set
	 */
	public void setCampoBroadcast(boolean campoBroadcast) {
		this.campoBroadcast = campoBroadcast;
	}

	/**
	 * @return the campoAno
	 */
	public boolean isCampoAno() {
		return campoAno;
	}

	/**
	 * @param campoAno the campoAno to set
	 */
	public void setCampoAno(boolean campoAno) {
		this.campoAno = campoAno;
	}

	/**
	 * @return the mapGenerosPermitidos
	 */
	public Map<String, Boolean> getMapGenerosPermitidos() {
		return mapGenerosPermitidos;
	}

	/**
	 * @param mapGenerosPermitidos the mapGenerosPermitidos to set
	 */
	public void setMapGenerosPermitidos(Map<String, Boolean> mapGenerosPermitidos) {
		this.mapGenerosPermitidos = mapGenerosPermitidos;
	}

	/**
	 * @return the campoGenero
	 */
	public boolean isCampoGenero() {
		return campoGenero;
	}

	/**
	 * @param campoGenero the campoGenero to set
	 */
	public void setCampoGenero(boolean campoGenero) {
		this.campoGenero = campoGenero;
	}
	
}
