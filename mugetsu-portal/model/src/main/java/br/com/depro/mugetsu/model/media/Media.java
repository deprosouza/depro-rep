package br.com.depro.mugetsu.model.media;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.cenum.Origem;
import br.com.depro.mugetsu.model.media.broadcast.MediaBroadcast;
import br.com.depro.mugetsu.model.media.nome.MediaNome;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Inicial - 17/06/2012
 */
@Entity
@XmlRootElement(name = "media")
public class Media extends EntidadeBase {

    /**
     * Numero de seire da classe
     */
    private static final long serialVersionUID = 5936866609232476359L;
    private int quantidedaEpisodios;
    private int quantidadeCapitulos;
    private int quantidadeVolumes;
    private int quantidadeTemporadas;
    private int ano;
    private int duracao;
    private String sinopse;
    private String nomePrincipal;
    private String idOrigem;
    private String pathImagem;
    private Origem origem;
    private FormatoMedia formatoMedia;
    private FormatoAnime formatoAnime;
    private FormatoDorama formatoDorama;
    private MediaConfiguration configuration;
    private Set<Tag> temas = new HashSet<Tag>();
    private Set<Genero> generos = new HashSet<Genero>();
    private List<Volume> volumes = new ArrayList<Volume>();
    private List<MediaNome> nomes = new ArrayList<MediaNome>();
    private List<Episodio> episodios = new ArrayList<Episodio>();
    private List<Capitulo> capitulos = new ArrayList<Capitulo>();
    private List<Galeria> galeriaImagens = new ArrayList<Galeria>();
    private List<MediaBroadcast> broadcasts = new ArrayList<MediaBroadcast>();

    /**
     * Retorna o formato real da media
     *
     * @return {@link String}
     */
    @Transient
    public String getTipoMedia() {
        String formato = this.getFormatoMedia().getDescricao();
        if (this.getFormatoMedia().equals(FormatoMedia.ANIME)) {
            formato += "-" + this.getFormatoAnime().getDescricao();
        } else if (this.getFormatoMedia().equals(FormatoMedia.DORAMA)) {
            formato += "-" + this.getFormatoDorama().getDescricao();
        }
        return formato;
    }
    
    /**
     * 
     * @return
     */
    @Transient
    public String getNomeOrigem() {
    	return getOrigem().getDescricao();
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @return the quantidedaEpisodios
     */
    @XmlElement
    public int getQuantidedaEpisodios() {
        return quantidedaEpisodios;
    }

    /**
     * @param quantidedaEpisodios the quantidedaEpisodios to set
     */
    public void setQuantidedaEpisodios(int quantidedaEpisodios) {
        this.quantidedaEpisodios = quantidedaEpisodios;
    }

    /**
     * @return the quantidadeCapitulos
     */
    @XmlElement
    public int getQuantidadeCapitulos() {
        return quantidadeCapitulos;
    }

    /**
     * @param quantidadeCapitulos the quantidadeCapitulos to set
     */
    public void setQuantidadeCapitulos(int quantidadeCapitulos) {
        this.quantidadeCapitulos = quantidadeCapitulos;
    }

    /**
     * @return the quantidadeVolumes
     */
    @XmlElement
    public int getQuantidadeVolumes() {
        return quantidadeVolumes;
    }

    /**
     * @param quantidadeVolumes the quantidadeVolumes to set
     */
    public void setQuantidadeVolumes(int quantidadeVolumes) {
        this.quantidadeVolumes = quantidadeVolumes;
    }

    /**
     * @return the quantidadeTemporadas
     */
    @XmlElement
    public int getQuantidadeTemporadas() {
        return quantidadeTemporadas;
    }

    /**
     * @param quantidadeTemporadas the quantidadeTemporadas to set
     */
    public void setQuantidadeTemporadas(int quantidadeTemporadas) {
        this.quantidadeTemporadas = quantidadeTemporadas;
    }

    /**
     * @return the ano
     */
    @XmlElement
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the duracao
     */
    @XmlElement
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * @return the sinopse
     */
    @XmlElement
    @Column(columnDefinition = "LONGTEXT")
    public String getSinopse() {
        return sinopse;
    }

    /**
     * @param sinopse the sinopse to set
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    /**
     * @return the nomes
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    @XmlElementWrapper(name = "nomes")
    @XmlElement(name = "nome")
    public List<MediaNome> getNomes() {
        return nomes;
    }

    /**
     * @param nomes the nomes to set
     */
    public void setNomes(List<MediaNome> nomes) {
        this.nomes = nomes;
    }

    /**
     * @return the formatoMedia
     */
    @XmlElement
    @Enumerated(EnumType.STRING)
    public FormatoMedia getFormatoMedia() {
        return formatoMedia;
    }

    /**
     * @param formatoMedia the formatoMedia to set
     */
    public void setFormatoMedia(FormatoMedia formatoMedia) {
        this.formatoMedia = formatoMedia;
    }

    /**
     * @return the formatoAnime
     */
    @XmlElement
    @Enumerated(EnumType.STRING)
    public FormatoAnime getFormatoAnime() {
        return formatoAnime;
    }

    /**
     * @param formatoAnime the formatoAnime to set
     */
    public void setFormatoAnime(FormatoAnime formatoAnime) {
        this.formatoAnime = formatoAnime;
    }

    /**
     * @return the formatoDorama
     */
    @XmlElement
    @Enumerated(EnumType.STRING)
    public FormatoDorama getFormatoDorama() {
        return formatoDorama;
    }

    /**
     * @param formatoDorama the formatoDorama to set
     */
    public void setFormatoDorama(FormatoDorama formatoDorama) {
        this.formatoDorama = formatoDorama;
    }

    /**
     * @return the generos
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MediaGenero", joinColumns =
    @JoinColumn(name = "media_fk"), inverseJoinColumns =
    @JoinColumn(name = "genero_fk"))
    @XmlElementWrapper(name = "generos")
    @XmlElement(name = "genero")
    public Set<Genero> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }

    /**
     * @return the temas
     */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "MediaTema", joinColumns =
    @JoinColumn(name = "media_fk"), inverseJoinColumns =
    @JoinColumn(name = "tema_fk"))
    @XmlElementWrapper(name = "temas")
    @XmlElement(name = "tema")
    public Set<Tag> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(Set<Tag> temas) {
        this.temas = temas;
    }

    /**
     * @return the broadcasts
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_fk")
    @XmlElementWrapper(name = "broadcasts")
    @XmlElement(name = "broadcast")
    public List<MediaBroadcast> getBroadcasts() {
        return broadcasts;
    }

    /**
     * @param broadcasts the broadcasts to set
     */
    public void setBroadcasts(List<MediaBroadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    /**
     * @return the episodios
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    @XmlElementWrapper(name = "episodios")
    @XmlElement(name = "episodio")
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    /**
     * @param episodios the episodios to set
     */
    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    /**
	 * @return the volumes
	 */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    @XmlElementWrapper(name = "volumes")
    @XmlElement(name = "volume")
	public List<Volume> getVolumes() {
		return volumes;
	}

	/**
	 * @return the capitulos
	 */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    @XmlElementWrapper(name = "capitulos")
    @XmlElement(name = "capitulo")
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	/**
	 * @param volumes the volumes to set
	 */
	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	/**
	 * @param capitulos the capitulos to set
	 */
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	/**
     * @return the pathImagem
     */
    @XmlTransient
    public String getPathImagem() {
        String retorno = "";
        for (Galeria imagem : getGaleriaImagens()) {
            if (imagem.isImagemAtual()) {
                retorno = imagem.getPathImagem();
            }
        }
        if (StringUtils.isBlank(retorno)) {
            retorno = pathImagem;
        }
        return retorno;
    }

    /**
     * @param pathImagem the pathImagem to set
     */
    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    /**
     * @return the configuration
     * @throws ApplicationException
     */
    @XmlTransient
    @Transient
    public MediaConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(MediaConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @return the origem
     */
    @XmlElement
    @Enumerated(EnumType.STRING)
    public Origem getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    /**
     * @return the nomePrincipal
     */
    @XmlElement
    public String getNomePrincipal() {
    	if (nomePrincipal == null) {
	         for (MediaNome item : this.nomes) {
	             if (item.isPrincipal()) {
	            	 nomePrincipal = item.getNome();
	                 break;
	             }
	         }
    	}
        return nomePrincipal;
    }

    /**
     * @param nomePrincipal the nomePrincipal to set
     */
    public void setNomePrincipal(String nomePrincipal) {
        this.nomePrincipal = nomePrincipal;
    }

    /**
     * @return the geleriaImagens
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    @XmlElementWrapper(name = "galeria")
    @XmlElement(name = "imagem")
    public List<Galeria> getGaleriaImagens() {
        return galeriaImagens;
    }

    /**
     * @param geleriaImagens the geleriaImagens to set
     */
    public void setGaleriaImagens(List<Galeria> geleriaImagens) {
        this.galeriaImagens = geleriaImagens;
    }

    /**
     * @return the idOrigem
     */
    @XmlElement
    public String getIdOrigem() {
        return idOrigem;
    }

    /**
     * @param idOrigem the idOrigem to set
     */
    public void setIdOrigem(String idOrigem) {
        this.idOrigem = idOrigem;
    }

}
