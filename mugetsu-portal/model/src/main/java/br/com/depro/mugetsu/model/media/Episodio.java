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
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.media.broadcast.EpisodiosBroadcast;
import br.com.depro.mugetsu.model.media.nome.EpisodioNome;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17.07.2012
 */
@Entity
@XmlRootElement(name = "episodio")
public class Episodio extends EntidadeBase {

    /** Numero de serie da classes */
	private static final long serialVersionUID = 3249804625741315346L;
	private Integer numero;
	private List<EpisodioNome> nomes = new ArrayList<EpisodioNome>();
	private List<EpisodiosBroadcast> broadcasts = new ArrayList<EpisodiosBroadcast>();
    private boolean filler;
    private String descricao;

    /**
     * @return the id
     */
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
	 * @return the nomes
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@JoinColumn(name = "episodio_fk")
	public List<EpisodioNome> getNomes() {
		return nomes;
	}

	/**
	 * @return the broadcasts
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@JoinColumn(name = "episodio_fk")
	public List<EpisodiosBroadcast> getBroadcasts() {
		return broadcasts;
	}

	/**
	 * @return the filler
	 */
	public boolean isFiller() {
		return filler;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
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
	public void setNomes(List<EpisodioNome> nomes) {
		this.nomes = nomes;
	}

	/**
	 * @param broadcasts the broadcasts to set
	 */
	public void setBroadcasts(List<EpisodiosBroadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

	/**
	 * @param filler the filler to set
	 */
	public void setFiller(boolean filler) {
		this.filler = filler;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
