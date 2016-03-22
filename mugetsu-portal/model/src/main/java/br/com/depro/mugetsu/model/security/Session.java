package br.com.depro.mugetsu.model.security;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.Status;

/**
 * Classe que representa a sessao do usuario da aplicacao <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
@Entity
public class Session extends EntidadeBase {
	
	private Date dataInicio;

	private Date datatFim;
	
	private String remoteAddress;
	
	private String numeroControle;
	
	private Status status;
	
	private Login login;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the dataInicio
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the datatFim
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDatatFim() {
		return datatFim;
	}

	/**
	 * @param datatFim the datatFim to set
	 */
	public void setDatatFim(Date datatFim) {
		this.datatFim = datatFim;
	}

	/**
	 * @return the remoteAddress
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * @param remoteAddress the remoteAddress to set
	 */
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	/**
	 * @return the numeroControle
	 */
	public String getNumeroControle() {
		return numeroControle;
	}

	/**
	 * @param numeroControle the numeroControle to set
	 */
	public void setNumeroControle(String numeroControle) {
		this.numeroControle = numeroControle;
	}

	/**
	 * @return the login
	 */
	@ManyToOne
	@JoinColumn
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
	 * @return the status
	 */
	@Enumerated(EnumType.STRING)
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
