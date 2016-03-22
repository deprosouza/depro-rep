package br.com.depro.mugetsu.model.media.nome;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "nome")
public class CapituloNome extends NomeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 3666268296556222646L;

}
