package br.com.depro.mugetsu.web.renderer;

import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.mugetsu.web.composite.pojo.ListMediaPojo;
import br.com.depro.mugetsu.web.utils.TipoEvento;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 18.08.2012
 */
public class RendererListboxMediaRecomentada implements ListitemRenderer<ListMediaPojo> {

    /**
     * @see ListitemRenderer#render(Listitem, Object, int)
     */
    public void render(Listitem item, ListMediaPojo data, int index)
            throws Exception {
        item.appendChild(new Listcell(Labels.getLabel(data.getTipoMedia())));
        item.appendChild(new Listcell(data.getTituloPrincipal().getNome()));
        if (StringUtils.isNotBlank(data.getNomeImagem())) {
            Image img = new Image();
            img.setContent(ViewHandler.getImage(TipoEvento.EXIBICAO, data.getNomeImagem()));
            Listcell listcell = new Listcell();
            listcell.appendChild(img);
            item.appendChild(listcell);
        } else {
            item.appendChild(new Listcell("N/A"));
        }
//		item.addEventListener(Events.ON_CLICK, ViewHandler.redirectID(MapConstantes.PARAM_ID_MEDIA,
//				NavigateHandler.VIEW_DETALHE_MEDIA_PRINCIPAL, data.getId()));
    }
}
