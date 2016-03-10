package br.com.depro.mugetsu.model.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.LocaleEnum;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "ALTERNATIVE_NAME")
public class AlternativeName extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = -8274039030525558913L;
	private String nome;
	private Boolean isPrincipal = Boolean.FALSE;
	private LocaleEnum locale;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "IS_PRINCIPAL")
	public Boolean getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	@Column(name = "LOCALE")
	@Enumerated(EnumType.STRING)
	public LocaleEnum getLocale() {
		return locale;
	}

	public void setLocale(LocaleEnum localidade) {
		this.locale = localidade;
	}
	
}
