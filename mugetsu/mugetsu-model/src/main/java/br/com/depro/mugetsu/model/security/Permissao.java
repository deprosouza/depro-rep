package br.com.depro.mugetsu.model.security;

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
@Table(name = "SEC_PERMISSAO")
public class Permissao extends EntidadeBase {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = 6326016710556595831L;
	private String key;
	private String descricao;
	private boolean habilitado;
	private Long nivel;
	private List<Role> roles;

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SEC_PERMISSAO")
    @SequenceGenerator(name = "SEQ_SEC_PERMISSAO", sequenceName = "SEQ_SEC_PERMISSAO")
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

	@Column(name = "IS_HABILITADO")
	public boolean isHabilitado() {
		return habilitado;
	}

	@Column(name = "NIVEL")
	public Long getNivel() {
		return nivel;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLE_PERMISSAO", joinColumns = @JoinColumn(name = "FK_PERMISSAO"), inverseJoinColumns = @JoinColumn(name = "FK_ROLE"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
