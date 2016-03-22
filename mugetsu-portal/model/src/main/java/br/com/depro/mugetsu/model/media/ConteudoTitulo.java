package br.com.depro.mugetsu.model.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.Localidade;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17.07.2012
 */
@Entity
@XmlRootElement(name = "conteudotitulo")
public class ConteudoTitulo extends EntidadeBase {

	private String nome;
	private Localidade localidade;
	private boolean principal;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the nome
	 */
	@XmlElement
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the localidade
	 */
	@ManyToOne
	@JoinColumn
	@XmlElement
	public Localidade getLocalidade() {
		return localidade;
	}
	
	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return the principal
	 */
	@XmlElement
	public boolean isPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}
