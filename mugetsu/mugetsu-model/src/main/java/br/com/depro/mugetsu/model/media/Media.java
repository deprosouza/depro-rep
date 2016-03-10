package br.com.depro.mugetsu.model.media;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

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
	private FormatoMediaEnum formatoMedia;
	private FormatoAnimeEnum formatoAnime;
	private Set<Tag> tags;
	private Set<Genero> generos;
	private List<AlternativeName> nomes;
	

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}
