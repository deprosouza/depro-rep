package br.com.depro.mugetsu.web.zk.tree;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import br.com.depro.mugetsu.web.security.SessionWorkspace;
import br.com.depro.mugetsu.web.zk.tree.schema.ILabelElement;
import br.com.depro.mugetsu.web.zk.tree.schema.IMenuDomain;
import br.com.depro.mugetsu.web.zk.tree.schema.MenuDomain;

abstract public class ZkossMenuFactory implements Serializable {

    private static final long serialVersionUID = 142621423557135573L;
    final private LinkedList<Component> stack;
    final private SessionWorkspace workspace;

    protected ZkossMenuFactory(Component component) {
        super();
        this.workspace = SessionWorkspace.getInstance();

        assert component != null : "Parent component is null!";
        assert this.workspace != null : "No UserWorkspace exists!";

        this.stack = new LinkedList<Component>();
        push(component);

        createMenu(MetaMenuFactory.getRootMenuDomain().getItems());


    }

    private void createMenu(List<IMenuDomain> items) {
        if (items.isEmpty()) {
            return;
        }
        for (final IMenuDomain menuDomain : items) {
            if (menuDomain instanceof MenuDomain) {
                final MenuDomain menu = (MenuDomain) menuDomain;
                if (addSubMenuImpl(menu)) {
                    createMenu(menu.getItems());
                    ebeneHoch();
                }
            } else {
                addItemImpl(menuDomain);
            }
        }
    }

    private void addItemImpl(IMenuDomain itemDomain) {
        if (isAllowed(itemDomain)) {
            setAttributes(itemDomain, createItemComponent(getCurrentComponent()));
        }
    }

    abstract protected ILabelElement createItemComponent(Component parent);

    private boolean addSubMenuImpl(MenuDomain menu) {
        if (isAllowed(menu)) {
            final MenuFactoryDto dto = createMenuComponent(getCurrentComponent(), menu.isOpen());

            setAttributes(menu, dto.getNode());

            push(dto.getParent());

            return true;
        }
        return false;
    }

    abstract protected MenuFactoryDto createMenuComponent(Component parent, boolean open);

    private boolean isAllowed(IMenuDomain treecellValue) {
        return isAllowed(treecellValue.getRightName());
    }

    private void ebeneHoch() {
        poll();
    }

    private Component getCurrentComponent() {
        return peek();
    }

    private SessionWorkspace getWorkspace() {
        return this.workspace;
    }

    private boolean isAllowed(String rightName) {
        if (StringUtils.isEmpty(rightName)) {
            return true;
        }
        return getWorkspace().isAllowed(rightName);
    }

    private Component peek() {
        return this.stack.peek();
    }

    private Component poll() {
        try {
            return this.stack.poll();
        } finally {
            if (this.stack.isEmpty()) {
                throw new RuntimeException("Root no longer exists!");
            }
        }
    }

    private void push(Component e) {
        this.stack.push(e);
    }

    protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
        if (treecellValue.isWithOnClickAction() == null || treecellValue.isWithOnClickAction().booleanValue()) {
            defaultTreecell.setZulNavigation(treecellValue.getZulNavigation());
            defaultTreecell.setIdComponent(treecellValue.getId());
            if (!StringUtils.isEmpty(treecellValue.getIconName())) {
                defaultTreecell.setImage(treecellValue.getIconName());
            }
        }

        setAttributesWithoutAction(treecellValue, defaultTreecell);
    }

    private void setAttributesWithoutAction(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
        assert treecellValue.getId() != null : "In mainmenu.xml file is a node who's ID is missing!";

        defaultTreecell.setId(treecellValue.getId());
        String label = treecellValue.getLabel();
        if (StringUtils.isEmpty(label)) {
            label = Labels.getLabel(treecellValue.getId());
        } else {
            label = Labels.getLabel(label);
        }
        defaultTreecell.setLabel(" " + label);
    }
}
