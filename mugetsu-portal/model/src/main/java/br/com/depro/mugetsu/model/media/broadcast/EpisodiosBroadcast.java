package br.com.depro.mugetsu.model.media.broadcast;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "episodiobroadcast")
public class EpisodiosBroadcast extends BroadcastBase {

	/** Numero de srie da classe */
	private static final long serialVersionUID = -3259031791117776763L;

}
