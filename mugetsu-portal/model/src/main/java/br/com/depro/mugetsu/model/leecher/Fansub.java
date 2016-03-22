package br.com.depro.mugetsu.model.leecher;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 14.08.2012
 */
@Entity
public class Fansub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	private String url;
	
	@Column(columnDefinition = "LONGTEXT")
	private String descricao;
	
	private Date dataCriacao;

	/**
	 * Contrutor padrao da classe
	 */
	public Fansub() {
		super();
	}

	/**
	 * Contrutor da classe
	 * 
	 * @param grupo
	 */
	public Fansub(Grupo grupo) {
		this.nome = grupo.getNome();
		this.url = grupo.getHomePage();
		this.descricao = grupo.getDescricao();
		this.dataCriacao = grupo.getDataCriacao();
	}



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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
