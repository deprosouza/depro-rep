package br.com.depro.mugetsu.model.leecher;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.depro.mugetsu.model.cenum.Formato;
import br.com.depro.mugetsu.model.security.Login;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 14.08.2012
 */
@Entity
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String url;
	
	private boolean isLinkQuebrado;
	
	private Date dataCriacao;
	
	@ManyToOne
	@JoinColumn
	private Servidor servidor;
	
	@ManyToOne
	@JoinColumn
	private Login login;
	
	private Formato formato;
	
	@ManyToOne
	@JoinColumn
	private Fansub fansub;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the servidor
	 */
	public Servidor getServidor() {
		return servidor;
	}

	/**
	 * @param servidor the servidor to set
	 */
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	/**
	 * @return the isLinkQuebrado
	 */
	public boolean isLinkQuebrado() {
		return isLinkQuebrado;
	}

	/**
	 * @param isLinkQuebrado the isLinkQuebrado to set
	 */
	public void setLinkQuebrado(boolean isLinkQuebrado) {
		this.isLinkQuebrado = isLinkQuebrado;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return the formato
	 */
	public Formato getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	/**
	 * @return the fansub
	 */
	public Fansub getFansub() {
		return fansub;
	}

	/**
	 * @param fansub the fansub to set
	 */
	public void setFansub(Fansub fansub) {
		this.fansub = fansub;
	}
}
