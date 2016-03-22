package br.com.depro.mugetsu.model.media;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.Localidade;


/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Incial - 18/06/2012
 */
@Entity
@XmlRootElement(name = "broadcast")
public class Broadcast extends EntidadeBase {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = 2277789579846296467L;
	private Date lacamento;
	private Date encerramento;
	private Localidade localidade;
	private String areaTrasmissao;
	private String emissora;
	private String patternLancamento;
	private String patternEncerramento;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the lacamento
	 */
	@Temporal(value = TemporalType.DATE)
	@XmlElement
	public Date getLacamento() {
		return lacamento;
	}
	
	/**
	 * @param lacamento the lacamento to set
	 */
	public void setLacamento(Date lacamento) {
		this.lacamento = lacamento;
	}
	
	/**
	 * @return the encerramento
	 */
	@Temporal(value = TemporalType.DATE)
	@XmlElement
	public Date getEncerramento() {
		return encerramento;
	}
	
	/**
	 * @param encerramento the encerramento to set
	 */
	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}
	
	/**
	 * @return the localidade
	 */
	@ManyToOne
	@JoinColumn
	@XmlElement
	public Localidade getLocalidade() {
		return localidade;
	}
	
	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return the emissora
	 */
	@XmlElement
	public String getEmissora() {
		return emissora;
	}

	/**
	 * @param emissora the emissora to set
	 */
	public void setEmissora(String emissora) {
		this.emissora = emissora;
	}

	/**
	 * @return the areaTrasmissao
	 */
	@XmlElement
	public String getAreaTrasmissao() {
		return areaTrasmissao;
	}

	/**
	 * @return the patternLancamento
	 */
	@XmlElement
	public String getPatternLancamento() {
		return patternLancamento;
	}

	/**
	 * @return the patternEncerramento
	 */
	@XmlElement
	public String getPatternEncerramento() {
		return patternEncerramento;
	}

	/**
	 * @param areaTrasmissao the areaTrasmissao to set
	 */
	public void setAreaTrasmissao(String areaTrasmissao) {
		this.areaTrasmissao = areaTrasmissao;
	}

	/**
	 * @param patternLancamento the patternLancamento to set
	 */
	public void setPatternLancamento(String patternLancamento) {
		this.patternLancamento = patternLancamento;
	}

	/**
	 * @param patternEncerramento the patternEncerramento to set
	 */
	public void setPatternEncerramento(String patternEncerramento) {
		this.patternEncerramento = patternEncerramento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emissora == null) ? 0 : emissora.hashCode());
		result = prime * result
				+ ((encerramento == null) ? 0 : encerramento.hashCode());
		result = prime * result
				+ ((lacamento == null) ? 0 : lacamento.hashCode());
		result = prime * result
				+ ((localidade == null) ? 0 : localidade.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Broadcast other = (Broadcast) obj;
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
	
}
