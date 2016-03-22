package br.com.depro.mugetsu.model.media;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.Localidade;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17/06/2012
 */
@Entity
@XmlRootElement(name = "titulo")
public class MediaTitulo extends EntidadeBase {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = -2039501117818862419L;
	private String nome;
	private boolean principal;
	private Localidade localidade;
	private Media media;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the nome
	 */
	@XmlElement
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
	 * @return the principal
	 */
	@XmlElement
	public boolean isPrincipal() {
		return principal;
	}
	
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	/**
	 * @return the localidade
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
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
	 * @return the media
	 */
	@ManyToOne
	@JoinColumn
	@XmlTransient
	public Media getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(Media media) {
		this.media = media;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		MediaTitulo other = (MediaTitulo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equalsIgnoreCase(other.nome))
			return false;
		return true;
	}
}
