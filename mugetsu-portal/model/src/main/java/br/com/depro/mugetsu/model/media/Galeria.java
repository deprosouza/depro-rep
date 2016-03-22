package br.com.depro.mugetsu.model.media;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.media.vo.FormatoImagem;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11/02/2013
 */
@Entity
@Table
@XmlRootElement(name = "imagem")
public class Galeria extends EntidadeBase {

	private String pathImagem;
	private Date dataUpload;
	private boolean imagemAtual;
	private FormatoImagem formatoImagem;
	
	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the pathImagem
	 */
	@XmlElement
	public String getPathImagem() {
		return pathImagem;
	}

	/**
	 * @return the dataUpload
	 */
	@XmlElement
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataUpload() {
		return dataUpload;
	}

	/**
	 * @return the imagemAtual
	 */
	@XmlElement
	public boolean isImagemAtual() {
		return imagemAtual;
	}

	/**
	 * @return the formatoImagem
	 */
	@XmlElement
	@Enumerated(EnumType.STRING)
	public FormatoImagem getFormatoImagem() {
		return formatoImagem;
	}

	/**
	 * @param pathImagem the pathImagem to set
	 */
	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	/**
	 * @param dataUpload the dataUpload to set
	 */
	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	/**
	 * @param imagemAtual the imagemAtual to set
	 */
	public void setImagemAtual(boolean imagemAtual) {
		this.imagemAtual = imagemAtual;
	}

	/**
	 * @param formatoImagem the formatoImagem to set
	 */
	public void setFormatoImagem(FormatoImagem formatoImagem) {
		this.formatoImagem = formatoImagem;
	}
	
}
