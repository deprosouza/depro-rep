package br.com.depro.ffsniffer.web.zk.tree.schema;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
@XmlRootElement(name = "XMLRootMenu", namespace = "br.com.depro.ffsnifer.web.view.zk")
public class RootMenuDomain {

	private List<IMenuDomain> items;

	@XmlElements( { @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
	public List<IMenuDomain> getItems() {
		return this.items;
	}

	public void setItems(List<IMenuDomain> items) {
		this.items = items;
	}
}
