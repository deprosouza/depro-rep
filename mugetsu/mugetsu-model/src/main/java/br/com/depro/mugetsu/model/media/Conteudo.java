package br.com.depro.mugetsu.model.media;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "CONTEUDO")
public class Conteudo extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = -5546821980020586992L;
	private String nomePrincipal;
	private String sequencia;
	private Integer paginas;
	private List<AlternativeName> nomes;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CONTEUDO")
	@SequenceGenerator(name = "SEQ_CONTEUDO", sequenceName = "SEQ_CONTEUDO")
	public Long getId() {
		return id;
	}

	@Column(name = "NOME_PRINCIPAL")
	public String getNomePrincipal() {
		return nomePrincipal;
	}

	@Column(name = "SEQUENCIA")
	public String getSequencia() {
		return sequencia;
	}

	@Column(name = "PAGINAS")
	public Integer getPaginas() {
		return paginas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_CONTEUDO")
	public List<AlternativeName> getNomes() {
		return nomes;
	}

	public void setNomePrincipal(String nomePrincipal) {
		this.nomePrincipal = nomePrincipal;
	}

	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public void setNomes(List<AlternativeName> nomes) {
		this.nomes = nomes;
	}

}
