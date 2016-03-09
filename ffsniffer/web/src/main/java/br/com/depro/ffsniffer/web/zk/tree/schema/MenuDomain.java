package br.com.depro.ffsniffer.web.zk.tree.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public class MenuDomain extends MenuItemDomain {

	private boolean open = true;
	private List<IMenuDomain> items = new ArrayList<IMenuDomain>();

	@XmlElements({ @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
	public List<IMenuDomain> getItems() {
		return this.items;
	}

	public void setItems(List<IMenuDomain> items) {
		this.items = items;
	}

	@XmlAttribute
	public boolean isOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
