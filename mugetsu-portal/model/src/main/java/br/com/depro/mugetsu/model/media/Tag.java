package br.com.depro.mugetsu.model.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

@Entity
@XmlRootElement(name = "tag")
public class Tag extends EntidadeBase implements Comparable<Tag> {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 8248527985216522324L;
	private String tag;

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
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	public int compareTo(Tag o) {
		return o.getTag().compareTo(getTag());
	}
}
