package br.com.depro.mugetsu.model.security;

import java.util.List;

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

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.security.TipoAcessoEnum;

/**
 * Classe representa a permissao de usuario da aplicacao <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 14/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
@Entity
public class Permissao extends EntidadeBase {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = 6326016710556595831L;
	private String prefixo;
	private String descricao;
	private boolean habilitado;
	private Long nivel;
	private TipoAcessoEnum tipoSeguranca;
	private List<Role> roles;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the prefixo
	 */
	public String getPrefixo() {
		return prefixo;
	}

	/**
	 * @param prefixo the prefixo to set
	 */
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the habilitado
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * @return the nivel
	 */
	public Long getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the roles
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RolePermissao", joinColumns = @JoinColumn(name = "permissao_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the tipoSeguranca
	 */
	@Enumerated(value = EnumType.STRING)
	public TipoAcessoEnum getTipoSeguranca() {
		return tipoSeguranca;
	}

	/**
	 * @param tipoSeguranca the tipoSeguranca to set
	 */
	public void setTipoSeguranca(TipoAcessoEnum tipoSeguranca) {
		this.tipoSeguranca = tipoSeguranca;
	}
}
