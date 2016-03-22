package br.com.depro.mugetsu.model.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.Status;

/**
 * Classe que representa o login do usuario da aplicacao <br><br>
 * 
 * <strong>Historico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 * 
 */
@Entity
@XmlRootElement(name = "login")
public class Login extends EntidadeBase {

	private String email;
	
	private String username;
	
	private String senha;
	
	private Date dataCadastro;
	
	private Status status;
	
	private Usuario usuario = new Usuario();
	
	private List<Role> roles = new ArrayList<Role>();
	

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlTransient
	public Long getId() {
		return id;
	}

	/**
	 * @return the email
	 */
	@Column(unique = true, nullable = false)
	@XmlElement
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	@Column(unique = true)
	@XmlElement
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the senha
	 */
	@XmlTransient
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the dataCadastro
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	@XmlElement
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the status
	 */
	@XmlElement
	@Enumerated(value = EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the usuario
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	@XmlElement
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the roles
	 */
	@XmlTransient
	@ManyToMany
	@JoinTable(name = "LoginRole", joinColumns = @JoinColumn(name = "login_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
