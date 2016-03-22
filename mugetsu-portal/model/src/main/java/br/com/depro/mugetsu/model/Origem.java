package br.com.depro.mugetsu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 04.08.2012
 */
@Entity
public class Origem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idOrigem;
	
	private String siteOrigem;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date ultimaAtualizacao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idOrigem
	 */
	public Long getIdOrigem() {
		return idOrigem;
	}

	/**
	 * @param idOrigem the idOrigem to set
	 */
	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}

	/**
	 * @return the siteOrigem
	 */
	public String getSiteOrigem() {
		return siteOrigem;
	}

	/**
	 * @param siteOrigem the siteOrigem to set
	 */
	public void setSiteOrigem(String siteOrigem) {
		this.siteOrigem = siteOrigem;
	}

	/**
	 * @return the ultimaAtualizacao
	 */
	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	/**
	 * @param ultimaAtualizacao the ultimaAtualizacao to set
	 */
	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
}
