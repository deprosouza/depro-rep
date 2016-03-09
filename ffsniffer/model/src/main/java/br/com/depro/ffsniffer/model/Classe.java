package br.com.depro.ffsniffer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 09.03.2015
 */
@Entity
@Table(name = "CLASSE")
public class Classe extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = -7992567705307121484L;
	private String nome;
	private String versao;
	private Date dataInsercao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the versao
	 */
	public String getVersao() {
		return versao;
	}

	/**
	 * @return the dataInsercao
	 */
	@Temporal(TemporalType.DATE)
	public Date getDataInsercao() {
		return dataInsercao;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param versao the versao to set
	 */
	public void setVersao(String versao) {
		this.versao = versao;
	}

	/**
	 * @param dataInsercao the dataInsercao to set
	 */
	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}
	
}
