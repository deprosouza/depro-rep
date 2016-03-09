
package br.com.depro.ffsniffer.web.zk.tree.schema;

import org.zkoss.zk.ui.Component;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public interface ILabelElement extends Component {

	void setZulNavigation(String zulNavigation);
	void setLabel(String string);
	void setImage(String image);
	void setIdComponent(String idComponent);
}
