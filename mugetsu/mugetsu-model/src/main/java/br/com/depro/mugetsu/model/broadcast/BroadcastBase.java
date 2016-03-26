package br.com.depro.mugetsu.model.broadcast;

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
import br.com.depro.mugetsu.model.LocaleEnum;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Incial - 18/06/2012
 */
@Entity
@Table(name = "BROADCAST")
public class BroadcastBase extends EntidadeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 2277789579846296467L;
	private Date lacamento;
	private Date encerramento;
	private LocaleEnum localidade;
	private String emissora;
	private String patternLancamento;
	private String patternEncerramento;
	private TipoBroadcastEnum tipo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_BROADCAST")
	@SequenceGenerator(name = "SEQ_BROADCAST", sequenceName = "SEQ_BROADCAST")
	public Long getId() {
		return id;
	}

	@Column(name = "DT_LACAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLacamento() {
		return lacamento;
	}

	@Column(name = "DT_ENCERRAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEncerramento() {
		return encerramento;
	}

	@Column(name = "LOCALE")
	@Enumerated(EnumType.STRING)
	public LocaleEnum getLocalidade() {
		return localidade;
	}

	@Column(name = "EMISSORA")
	public String getEmissora() {
		return emissora;
	}

	@Column(name = "PATTERN_LACAMENTO")
	public String getPatternLancamento() {
		return patternLancamento;
	}

	@Column(name = "PATTERN_ENCERRAMENTO")
	public String getPatternEncerramento() {
		return patternEncerramento;
	}

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	public TipoBroadcastEnum getTipo() {
		return tipo;
	}

	public void setLacamento(Date lacamento) {
		this.lacamento = lacamento;
	}

	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}

	public void setLocalidade(LocaleEnum localidade) {
		this.localidade = localidade;
	}

	public void setEmissora(String emissora) {
		this.emissora = emissora;
	}

	public void setPatternLancamento(String patternLancamento) {
		this.patternLancamento = patternLancamento;
	}

	public void setPatternEncerramento(String patternEncerramento) {
		this.patternEncerramento = patternEncerramento;
	}

	public void setTipo(TipoBroadcastEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emissora == null) ? 0 : emissora.hashCode());
		result = prime * result + ((encerramento == null) ? 0 : encerramento.hashCode());
		result = prime * result + ((lacamento == null) ? 0 : lacamento.hashCode());
		result = prime * result + ((localidade == null) ? 0 : localidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BroadcastBase other = (BroadcastBase) obj;
		if (emissora == null) {
			if (other.emissora != null)
				return false;
		} else if (!emissora.equals(other.emissora))
			return false;
		if (encerramento == null) {
			if (other.encerramento != null)
				return false;
		} else if (!encerramento.equals(other.encerramento))
			return false;
		if (lacamento == null) {
			if (other.lacamento != null)
				return false;
		} else if (!lacamento.equals(other.lacamento))
			return false;
		if (localidade == null) {
			if (other.localidade != null)
				return false;
		} else if (!localidade.equals(other.localidade))
			return false;
		return true;
	}

	public enum TipoBroadcastEnum {

		MEDIA, CONTEUDO;
	}

}
