package br.com.depro.mugetsu.model.media.nome;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17.07.2012
 */
@Entity
@XmlRootElement(name = "episodionome")
public class EpisodioNome extends NomeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 7122772863606663546L;

}
