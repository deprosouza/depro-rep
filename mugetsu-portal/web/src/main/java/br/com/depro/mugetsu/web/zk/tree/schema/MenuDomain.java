package br.com.depro.mugetsu.web.zk.tree.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * @author bbruhns
 * 
 */
public class MenuDomain extends MenuItemDomain {

	private boolean open = true;

	@XmlElements({ @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
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

	private List<IMenuDomain> items = new ArrayList<IMenuDomain>();

	@XmlAttribute
	public boolean isOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
