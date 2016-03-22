package br.com.depro.mugetsu.web.renderer;

import org.zkoss.zul.Image;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.mugetsu.web.composite.pojo.ListConteudoPojo;

/**
 * 
 * @author rsouza
 * @version. - Versao Inicial - 09.09.2012
 */
public class RendererListboxConteudoSimples implements ListitemRenderer<ListConteudoPojo> {

	/**
	 * @see ListitemRenderer#render(Listitem, Object, int)
	 */
	public void render(Listitem item, ListConteudoPojo data, int index) throws Exception {
		item.appendChild(new Listcell(String.valueOf(data.getConteudo().getNumeroEpisodio())));
		item.appendChild(new Listcell(data.getTituloPrincipal().getNome()));
		Image image = new Image(RendererUtils.renderImagemCheckOrNotCheck(data, 2));
		Listcell listcell = new Listcell();
		listcell.appendChild(image);
		item.appendChild(listcell);
	}


}
