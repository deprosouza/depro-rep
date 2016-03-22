package br.com.depro.mugetsu.model.media;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.media.broadcast.CapituloBroadcast;
import br.com.depro.mugetsu.model.media.nome.CapituloNome;

@Entity
@XmlRootElement(name = "capitulo")
public class Capitulo extends EntidadeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 1004895556147428834L;
	private Integer numero;
	private List<CapituloNome> nomes;
	private List<CapituloBroadcast> broadcasts = new ArrayList<CapituloBroadcast>();

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @return the nomes
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "capitulo_fk")
	@XmlElementWrapper(name = "nomes")
	@XmlElement(name = "nome")
	public List<CapituloNome> getNomes() {
		return nomes;
	}

	/**
	 * @return the broadcasts
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "capitulo_fk")
	@XmlElementWrapper(name = "broadcasts")
	@XmlElement(name = "broadcast")
	public List<CapituloBroadcast> getBroadcasts() {
		return broadcasts;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @param nomes the nomes to set
	 */
	public void setNomes(List<CapituloNome> nomes) {
		this.nomes = nomes;
	}

	/**
	 * @param broadcasts the broadcasts to set
	 */
	public void setBroadcasts(List<CapituloBroadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

}
