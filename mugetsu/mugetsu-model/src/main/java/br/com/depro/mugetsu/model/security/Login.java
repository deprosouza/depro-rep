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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.security.util.StatusEnum;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.03.2016
 */
@Entity
@Table(name = "SEC_LOGIN")
public class Login extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = 5623173205050785236L;
	private String email;
	private String username;
	private String senha;
	private String chaveRecuperacao;
	private Date dataCadastro;
	private Date validadeChaveRecuperacao;
	private boolean resetSenha = true;
	private StatusEnum status;
	private Usuario usuario = new Usuario();
	private List<Role> roles = new ArrayList<Role>();
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SEC_LOGIN")
    @SequenceGenerator(name = "SEQ_SEC_LOGIN", sequenceName = "SEQ_SEC_LOGIN")
	public Long getId() {
		return id;
	}

	@Column(name = "EMAIL", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	@Column(name = "USERNAME", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	@Column(name = "SENHA")
	public String getSenha() {
		return senha;
	}

	@Column(name = "CHAVE_RECUPERACAO")
	public String getChaveRecuperacao() {
		return chaveRecuperacao;
	}

	@Column(name = "DT_CADASTRO")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	@Column(name = "DT_VALIDADE_RECUPERACAO")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getValidadeChaveRecuperacao() {
		return validadeChaveRecuperacao;
	}

	@Column(name = "IS_RESET_SENHA")
	public boolean isResetSenha() {
		return resetSenha;
	}

	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	public StatusEnum getStatus() {
		return status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USUARIO", unique = true)
	public Usuario getUsuario() {
		return usuario;
	}

	@ManyToMany
	@JoinTable(name = "LOGIN_ROLE", joinColumns = @JoinColumn(name = "FK_LOGIN"), inverseJoinColumns = @JoinColumn(name = "FK_ROLE"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setChaveRecuperacao(String chaveRecuperacao) {
		this.chaveRecuperacao = chaveRecuperacao;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setValidadeChaveRecuperacao(Date validadeChaveRecuperacao) {
		this.validadeChaveRecuperacao = validadeChaveRecuperacao;
	}

	public void setResetSenha(boolean resetSenha) {
		this.resetSenha = resetSenha;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
