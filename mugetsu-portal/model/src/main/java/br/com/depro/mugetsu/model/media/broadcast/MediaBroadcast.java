package br.com.depro.mugetsu.model.media.broadcast;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "broadcast")
public class MediaBroadcast extends BroadcastBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 834904395876494093L;

}