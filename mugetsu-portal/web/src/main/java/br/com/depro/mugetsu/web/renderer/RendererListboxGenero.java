package br.com.depro.mugetsu.web.renderer;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.mugetsu.model.media.Genero;

/**
 * Classe auxiliar para renderiza��o do lixtbox de genero<br><br>
 * 
 * <strong>Hist�rico
 * <ul>
 * 		<li><strong>@author rsouza @version 1.0 - Data: 27/04/2012</li>
 * </ul>
 * 
 * @since 1.0
 */
public class RendererListboxGenero implements ListitemRenderer<Genero> {

	/*
	 * (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
	 */
	public void render(Listitem item, Genero data, int index) throws Exception {
		item.appendChild(new Listcell());
		item.appendChild(new Listcell(data.getPrefixo()));
		item.setValue(data);
	}

}
