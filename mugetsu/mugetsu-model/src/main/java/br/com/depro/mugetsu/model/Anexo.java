package br.com.depro.mugetsu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "ANEXO")
public class Anexo extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = -8413804473375937080L;
	private String nome;
	private Boolean principal;
	private Date dataCriacao;
	private TipoAnexo tipoAnexo;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ANEXO")
	@SequenceGenerator(name = "SEQ_ANEXO", sequenceName = "SEQ_ANEXO")
	public Long getId() {
		return id;
	}

	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	@Column(name = "IS_PRINCIPAL")
	public Boolean getPrincipal() {
		return principal;
	}

	@Column(name = "DT_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	@Column(name = "TIPO_ANEXO")
	@Enumerated(EnumType.STRING)
	public TipoAnexo getTipoAnexo() {
		return tipoAnexo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setTipoAnexo(TipoAnexo tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}

	public enum TipoAnexo {
		
		IMAGEM,
		VIDEO,
		DOCUMENTO,
		MUSICA;
	}
}
