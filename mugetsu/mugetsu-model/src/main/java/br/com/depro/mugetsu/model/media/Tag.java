package br.com.depro.mugetsu.model.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 10.03.2016
 */
@Entity
@Table(name = "TAG")
public class Tag  extends EntidadeBase  {

	/** Numero serial da classe */
	private static final long serialVersionUID = -2039656547676677956L;
	private String key;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TAG")
	@SequenceGenerator(name = "SEQ_TAG", sequenceName = "SEQ_TAG")
	public Long getId() {
		return id;
	}

	@Column(name = "CHAVE")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}