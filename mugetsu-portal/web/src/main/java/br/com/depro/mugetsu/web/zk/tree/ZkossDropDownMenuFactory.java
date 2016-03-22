package br.com.depro.mugetsu.web.zk.tree;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Menupopup;

import br.com.depro.mugetsu.web.zk.tree.schema.ILabelElement;
import br.com.depro.mugetsu.web.zk.tree.schema.IMenuDomain;


public class ZkossDropDownMenuFactory extends ZkossMenuFactory {

	private static final long serialVersionUID = -6930474675371322560L;

	public static void addDropDownMenu(Component component) {
		new ZkossDropDownMenuFactory(component);
	}

	/**
	 * @param component
	 */
	private ZkossDropDownMenuFactory(Component component) {
		super(component);
	}

	@Override
	protected MenuFactoryDto createMenuComponent(Component parent, boolean open) {
		final DefaultDropDownMenu menu = new DefaultDropDownMenu();
		parent.appendChild(menu);

		final Menupopup menupopup = new Menupopup();
		menu.appendChild(menupopup);

		return new MenuFactoryDto(menupopup, menu);
	}

	@Override
	protected ILabelElement createItemComponent(Component parent) {
		final DefaultDropDownMenuItem item = new DefaultDropDownMenuItem();
		parent.appendChild(item);
		return item;
	}

	@Override
	protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
		super.setAttributes(treecellValue, defaultTreecell);
		defaultTreecell.setImage(treecellValue.getIconName());
	}

}
