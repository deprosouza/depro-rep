
package br.com.depro.mugetsu.web.zk.tree;

import org.zkoss.zk.ui.Component;

import br.com.depro.mugetsu.web.zk.tree.schema.ILabelElement;


public class MenuFactoryDto {

	public MenuFactoryDto(Component parent, ILabelElement node) {
		super();
		this.parent = parent;
		this.node = node;
	}

	public MenuFactoryDto(ILabelElement node) {
		this(node, node);
	}

	/**
	 * @return the parent
	 */
	public Component getParent() {
		return this.parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	/**
	 * @return the node
	 */
	public ILabelElement getNode() {
		return this.node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(ILabelElement node) {
		this.node = node;
	}

	private Component parent;
	private ILabelElement node;

}
