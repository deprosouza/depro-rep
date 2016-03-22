
package br.com.depro.mugetsu.web.zk.tree.schema;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "XMLRootMenu", namespace = "br.com.mugetsu.web.view.zk")
public class RootMenuDomain {

	private List<IMenuDomain> items;

	/**
	 * @return the items
	 */
	@XmlElements( { @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
	public List<IMenuDomain> getItems() {
		return this.items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<IMenuDomain> items) {
		this.items = items;
	}
}
