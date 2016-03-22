package br.com.depro.mugetsu.model.media.nome;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "nome")
public class VolumeNome extends NomeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 7215152323475452274L;

}
