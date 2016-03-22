package br.com.depro.mugetsu.web.renderer;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.web.composite.pojo.ListMediaPojo;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.NavigateHandler;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 28.07.2012
 */
public class RendererListboxMediaConsulta implements ListitemRenderer<ListMediaPojo> {

    /**
     * @see ListitemRenderer#render(Listitem, Object, int)
     */
    public void render(Listitem item, final ListMediaPojo data, int index) throws Exception {
        item.setValue(data);
        item.setSclass("itemClick");
        item.appendChild(new Listcell(Labels.getLabel(data.getTipoMedia())));
        item.appendChild(new Listcell(data.getNomePrincipal()));
        if (data.getFormatoMedia().equals(FormatoMedia.MANGA)) {
            int quantidade = data.getQuantidadeVolumes();
            String key = quantidade > 1 ? "label.volume.plural" : "label.volume";
            item.appendChild(new Listcell(data.getQuantidadeVolumes() + " " + Labels.getLabel(key)));
        } else if (data.getFormatoMedia().equals(FormatoMedia.FILME)) {
            item.appendChild(new Listcell(String.valueOf(data.getAno())));
        } else {
            int quantidade = data.getQuantidadeEpisodios();
            String key = quantidade > 1 ? "label.episodio.plural" : "label.episodio";
            item.appendChild(new Listcell(data.getQuantidadeEpisodios() + " " + Labels.getLabel(key)));
        }
        final Integer paginaItem = ((Listbox)item.getParent()).getActivePage();
        item.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
        	
            public void onEvent(Event event) throws Exception {
            	ViewHandler.putInSession(MapConstantes.PARAM_ID_MEDIA, data.getId());
            	ViewHandler.putInSession(MapConstantes.PARAM_PAGE_LIST_MEDIA, paginaItem);
                NavigateHandler.navigate(NavigateEnum.PAGE_DETALHE_MIDIA);
            }
        });
    }
}
