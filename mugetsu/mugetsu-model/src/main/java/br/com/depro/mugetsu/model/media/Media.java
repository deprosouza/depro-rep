package br.com.depro.mugetsu.model.media;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.Anexo;
import br.com.depro.mugetsu.model.media.util.FormatoAnime;
import br.com.depro.mugetsu.model.media.util.FormatoMedia;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "MEDIA")
public class Media extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = 1884364187471843431L;
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
	private Set<Tag> tags;
	private Set<Genero> generos;
	private List<AlternativeName> nomes;
	private List<Anexo> anexos;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEDIA")
	@SequenceGenerator(name = "SEQ_MEDIA", sequenceName = "SEQ_MEDIA")
	public Long getId() {
		return id;
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
	public FormatoMedia getFormatoMedia() {
		return formatoMedia;
	}

	@Column(name = "FORMATO_ANIME")
	public FormatoAnime getFormatoAnime() {
		return formatoAnime;
	}

	@ManyToMany
	@JoinTable(name = "MEDIA_TAGS", joinColumns = @JoinColumn(name = "FK_MEDIA"), inverseJoinColumns = @JoinColumn(name = "FK_TAG"))
	public Set<Tag> getTags() {
		return tags;
	}

	@ManyToMany
	@JoinTable(name = "MEDIA_GENERO", joinColumns = @JoinColumn(name = "FK_MEDIA"), inverseJoinColumns = @JoinColumn(name = "FK_GENERO"))
	public Set<Genero> getGeneros() {
		return generos;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_MEDIA")
	public List<AlternativeName> getNomes() {
		return nomes;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_MEDIA")
	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setNomePrincipal(String nomePrincipal) {
		this.nomePrincipal = nomePrincipal;
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

}
