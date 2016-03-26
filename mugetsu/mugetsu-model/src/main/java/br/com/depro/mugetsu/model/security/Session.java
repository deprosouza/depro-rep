package br.com.depro.mugetsu.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.security.util.StatusEnum;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.03.2016
 */
@Entity
@Table(name = "SEC_SESSION")
public class Session extends EntidadeBase {
	
	/** Numero seria da classe */
	private static final long serialVersionUID = 2330949211746546353L;
	private Date dataInicio;
	private Date datatFim;
	private String remoteAddress;
	private String numeroControle;
	private StatusEnum status;
	private Login login;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SEC_SESSION")
    @SequenceGenerator(name = "SEQ_SEC_SESSION", sequenceName = "SEQ_SEC_SESSION")
	public Long getId() {
		return id;
	}
	
	@Column(name = "DT_INICIO")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataInicio() {
		return dataInicio;
	}

	@Column(name = "DT_FIM")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDatatFim() {
		return datatFim;
	}

	@Column(name = "REMOTE_ADDRESS")
	public String getRemoteAddress() {
		return remoteAddress;
	}

	@Column(name = "NUM_CONTROLE")
	public String getNumeroControle() {
		return numeroControle;
	}

	@ManyToOne
	@JoinColumn(name = "FK_LOGIN")
	public Login getLogin() {
		return login;
	}

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	public StatusEnum getStatus() {
		return status;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDatatFim(Date datatFim) {
		this.datatFim = datatFim;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public void setNumeroControle(String numeroControle) {
		this.numeroControle = numeroControle;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
