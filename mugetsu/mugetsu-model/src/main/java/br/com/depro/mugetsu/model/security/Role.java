package br.com.depro.mugetsu.model.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.03.2016
 */
@Entity
@Table(name = "SEC_ROLE")
public class Role extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = 2036856675333358214L;
	private String key;
	private String descricao;
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SEC_ROLE")
    @SequenceGenerator(name = "SEQ_SEC_ROLE", sequenceName = "SEQ_SEC_ROLE")
	public Long getId() {
		return id;
	}

	@Column(name = "KEY")
	public String getKey() {
		return key;
	}

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return descricao;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLE_PERMISSAO", joinColumns = @JoinColumn(name = "FK_ROLE") , inverseJoinColumns = @JoinColumn(name = "FK_PERMISSAO") )
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}