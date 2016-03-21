package br.com.depro.mugetsu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private Boolean isCapa;

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "IS_CAPA")
	public Boolean getIsCapa() {
		return isCapa;
	}

	public void setIsCapa(Boolean isCapa) {
		this.isCapa = isCapa;
	}
	
	public enum TipoImagemEnum {
		
		MEDIA,
		CONTEUDO,
		USUARIO,
		GRUPO;
	}
}
