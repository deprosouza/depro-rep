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
import br.com.depro.mugetsu.model.media.broadcast.VolumeBroadcast;
import br.com.depro.mugetsu.model.media.nome.VolumeNome;

/**
 * @author rsouza
 */
@Entity
@XmlRootElement(name = "volume")
public class Volume extends EntidadeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = -5049743679422213223L;
	private Integer numero;
	private Integer paginas;
	private String nome;
	private List<VolumeNome> nomes = new ArrayList<VolumeNome>();
	private List<VolumeBroadcast> broadcasts = new ArrayList<VolumeBroadcast>();

	@Id
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
	 * @return the paginas
	 */
	public Integer getPaginas() {
		return paginas;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the nomes
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "volume_fk")
	@XmlElementWrapper(name = "nomes")
	@XmlElement(name = "nome")
	public List<VolumeNome> getNomes() {
		return nomes;
	}

	/**
	 * @return the broadcasts
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "volume_fk")
	@XmlElementWrapper(name = "nomes")
	@XmlElement(name = "nome")
	public List<VolumeBroadcast> getBroadcasts() {
		return broadcasts;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @param paginas the paginas to set
	 */
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param nomes the nomes to set
	 */
	public void setNomes(List<VolumeNome> nomes) {
		this.nomes = nomes;
	}

	/**
	 * @param broadcasts the broadcasts to set
	 */
	public void setBroadcasts(List<VolumeBroadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

}
