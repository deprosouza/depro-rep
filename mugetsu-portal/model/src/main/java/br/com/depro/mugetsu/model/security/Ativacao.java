package br.com.depro.mugetsu.model.security;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.Status;


/**
 * 
 * @author smart
 * @version 1.0 - Versao Inicial - 10.09.2012
 */
@Entity
@XmlRootElement(name = "ativacao")
public class Ativacao extends EntidadeBase {

	private String codigoAtivacao;
	
	private Login login;
	
	private Date dataCriacao;
	
	private Status status;
	
	/**
	 * Construtor padrao da classe
	 */
	public Ativacao() {
		super();
	}

	/**
	 * Construtor da classe
	 * 
	 * @param login
	 */
	public Ativacao(Login login) {
		super();
		this.login = login;
		this.dataCriacao = new Date();
		this.status = Status.PENDENTE;
		this.codigoAtivacao = UUID.randomUUID().toString() + Math.abs(this.hashCode());
	}
	
	/**
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	public Long getId() {
		return id;
	}

	/**
	 * @return the codigoAtivacao
	 */
	@XmlElement
	@Column(unique = true)
	public String getCodigoAtivacao() {
		return codigoAtivacao;
	}

	/**
	 * @param codigoAtivacao the codigoAtivacao to set
	 */
	public void setCodigoAtivacao(String codigoAtivacao) {
		this.codigoAtivacao = codigoAtivacao;
	}

	/**
	 * @return the login
	 */
	@XmlElement
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "login_fk")
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return the dataCriacao
	 */
	@XmlElement
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the status
	 */
	@XmlElement
	@Enumerated(value = EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
}
