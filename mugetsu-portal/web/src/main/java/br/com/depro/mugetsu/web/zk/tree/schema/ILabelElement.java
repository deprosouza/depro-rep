
package br.com.depro.mugetsu.web.zk.tree.schema;

import org.zkoss.zk.ui.Component;


public interface ILabelElement extends Component {

	void setZulNavigation(String zulNavigation);

	void setLabel(String string);

	void setImage(String image);
	
	void setIdComponent(String idComponent);
}
