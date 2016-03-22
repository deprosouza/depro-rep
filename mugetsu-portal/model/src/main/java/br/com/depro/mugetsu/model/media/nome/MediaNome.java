package br.com.depro.mugetsu.model.media.nome;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17/06/2012
 */
@Entity
@XmlRootElement(name = "titulo")
public class MediaNome extends NomeBase {
	
	/** Numero de serie da classe */
	private static final long serialVersionUID = -2039501117818862419L;
}
