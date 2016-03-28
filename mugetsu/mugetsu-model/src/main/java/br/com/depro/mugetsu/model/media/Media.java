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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.Anexo;
import br.com.depro.mugetsu.model.media.util.FormatoAnime;
import br.com.depro.mugetsu.model.media.util.FormatoDorama;
import br.com.depro.mugetsu.model.media.util.FormatoMedia;
import br.com.depro.mugetsu.model.media.util.OrigemEnum;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "MEDIA")
public class Media extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = 1884364187471843431L;
	private String idOrigem;
	private String sinopse;
	private String nomePrincipal;
	private Integer duracao;
	private Integer quantidadeEpisodios;
	private Integer quantidadeVolumes;
	private Integer quantidadeCapitulos;
	private Integer mediaPaginas;
	private Integer ano;
	private FormatoMedia formatoMedia;
	private FormatoAnime formatoAnime;
	private FormatoDorama formatoDorama;
	private OrigemEnum origem;
	private Set<Tag> tags = new HashSet<Tag>();
	private Set<Genero> generos = new HashSet<Genero>();
	private List<AlternativeName> nomes = new ArrayList<AlternativeName>();
	private List<Anexo> anexos = new ArrayList<Anexo>();
	private List<Conteudo> conteudos = new ArrayList<Conteudo>();
	private List<Broadcast> broadcasts = new ArrayList<Broadcast>();

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEDIA")
	@SequenceGenerator(name = "SEQ_MEDIA", sequenceName = "SEQ_MEDIA")
	public Long getId() {
		return id;
	}

	@Column(name = "ID_ORIGEM", length = 200)
	public String getIdOrigem() {
		return idOrigem;
	}

	@Column(name = "SINOPSE", length = 5000)
	public String getSinopse() {
		return sinopse;
	}

	@Column(name = "NOME_PRINCIPAL")
	public String getNomePrincipal() {
		return nomePrincipal;
	}

	@Column(name = "DURACAO")
	public Integer getDuracao() {
		return duracao;
	}

	@Column(name = "QTDE_EPISODIOS")
	public Integer getQuantidadeEpisodios() {
		return quantidadeEpisodios;
	}

	@Column(name = "QTDE_VOLUMES")
	public Integer getQuantidadeVolumes() {
		return quantidadeVolumes;
	}

	@Column(name = "QTDE_CAPITULOS")
	public Integer getQuantidadeCapitulos() {
		return quantidadeCapitulos;
	}

	@Column(name = "MEDIA_PAGINAS")
	public Integer getMediaPaginas() {
		return mediaPaginas;
	}

	@Column(name = "ANO")
	public Integer getAno() {
		return ano;
	}

	@Column(name = "FORMATO_MEDIA")
	@Enumerated(EnumType.STRING)
	public FormatoMedia getFormatoMedia() {
		return formatoMedia;
	}

	@Column(name = "FORMATO_ANIME")
	@Enumerated(EnumType.STRING)
	public FormatoAnime getFormatoAnime() {
		return formatoAnime;
	}

	@Column(name = "FORMATO_DORAMA")
	@Enumerated(EnumType.STRING)
	public FormatoDorama getFormatoDorama() {
		return formatoDorama;
	}

	@Column(name = "ORIGEM")
	@Enumerated(EnumType.STRING)
	public OrigemEnum getOrigem() {
		return origem;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "MEDIA_TAGS", joinColumns = @JoinColumn(name = "FK_MEDIA"), inverseJoinColumns = @JoinColumn(name = "FK_TAG"))
	public Set<Tag> getTags() {
		return tags;
	}

	@ManyToMany
	@JoinTable(name = "MEDIA_GENERO", joinColumns = @JoinColumn(name = "FK_MEDIA"), inverseJoinColumns = @JoinColumn(name = "FK_GENERO"))
	public Set<Genero> getGeneros() {
		return generos;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_MEDIA")
	public List<AlternativeName> getNomes() {
		return nomes;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_MEDIA")
	public List<Anexo> getAnexos() {
		return anexos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_MEDIA")
	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_MEDIA")
	public List<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	@Transient
	public List<Anexo> getImagens() {
		List<Anexo> anexos = new ArrayList<Anexo>();
		if (CollectionUtils.isNotEmpty(getAnexos())) {
			for (Anexo anexo : getAnexos()) {
				if (anexo.isImagem()) {
					anexos.add(anexo);
				}
			}
		}
		return anexos;
	}
	
	@Transient
	public Anexo getImagemPrincipal() {
		for (Anexo anexo : getImagens()) {
			if (anexo.getPrincipal()) {
				return anexo;
			}
		}
		return null;
	}
	
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setIdOrigem(String idOrigem) {
		this.idOrigem = idOrigem;
	}

	public void setNomePrincipal(String nomePrincipal) {
		this.nomePrincipal = nomePrincipal;
	}
	
	public void setNomePrincipal(AlternativeName alternativeName) {
		getNomes().add(alternativeName);
		if (alternativeName.getPrincipal()) {
			this.nomePrincipal = alternativeName.getNome();
		}
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public void setQuantidadeEpisodios(Integer quantidadeEpisodios) {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

	public void setQuantidadeVolumes(Integer quantidadeVolumes) {
		this.quantidadeVolumes = quantidadeVolumes;
	}

	public void setQuantidadeCapitulos(Integer quantidadeCapitulos) {
		this.quantidadeCapitulos = quantidadeCapitulos;
	}

	public void setMediaPaginas(Integer mediaPaginas) {
		this.mediaPaginas = mediaPaginas;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setFormatoMedia(FormatoMedia formatoMedia) {
		this.formatoMedia = formatoMedia;
	}

	public void setFormatoAnime(FormatoAnime formatoAnime) {
		this.formatoAnime = formatoAnime;
	}

	public void setFormatoDorama(FormatoDorama formatoDorama) {
		this.formatoDorama = formatoDorama;
	}

	public void setOrigem(OrigemEnum origem) {
		this.origem = origem;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setGeneros(Set<Genero> generos) {
		this.generos = generos;
	}

	public void setNomes(List<AlternativeName> nomes) {
		this.nomes = nomes;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public void setBroadcasts(List<Broadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

}
