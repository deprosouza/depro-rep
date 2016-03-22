package br.com.depro.mugetsu.web.zk.tree;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Messagebox;

import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.NavigateHandler;
import br.com.depro.mugetsu.web.zk.tree.schema.ILabelElement;

class DefaultDropDownMenuItem extends Menuitem implements EventListener, Serializable, ILabelElement {

    private static final long serialVersionUID = -2813840859147955432L;
    private String zulNavigation;
    @SuppressWarnings("unused")
    private String idComponent;

    public void onEvent(Event event) throws Exception {

        try {
            /* get an instance of the borderlayout defined in the zul-file */
            Div div = (Div) Path.getComponent("/winPrincipal").getFellow("conteudoPrincipal");

            if (!div.getChildren().isEmpty()) {
                Components.removeAllChildren(div);
            }

            String path = getZulNavigation();
            NavigateHandler.push(path);
            Executions.createComponents(NavigateEnum.getEnum(path).getUri(), div, null);
        } catch (final Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private String getZulNavigation() {
        return zulNavigation;
    }

    public void setZulNavigation(String zulNavigation) {
        this.zulNavigation = zulNavigation;
        if (!StringUtils.isEmpty(zulNavigation)) {
            addEventListener("onClick", this);
        }
    }

    public void setIdComponent(String idComponent) {
        this.idComponent = idComponent;
    }
}
