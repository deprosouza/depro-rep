package br.com.depro.mugetsu.web.renderer;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

import br.com.depro.mugetsu.model.Localidade;

/**
 * Classe auxiliar para renderiza��o do combobox de localidades <br><br>
 * 
 * <strong>Hist�rico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 01/03/2012</li>
 * </ul>
 * 
 * @since 1.0
 */
public class RendererComboboxLocalidade implements ComboitemRenderer<Localidade> {

	/**
	 * @see ComboitemRenderer#render(Comboitem, Object, int)
	 */
	public void render(Comboitem item, Localidade data, int index) throws Exception {
		item.setLabel(Labels.getLabel(data.getPrefixo()));
		item.setValue(data);
	}

}
