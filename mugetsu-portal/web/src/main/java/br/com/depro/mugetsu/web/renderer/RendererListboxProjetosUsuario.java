package br.com.depro.mugetsu.web.renderer;

import java.util.Date;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.depro.fw.typezero.infrastructure.utils.DataUtils;
import br.com.depro.mugetsu.web.composite.pojo.ListMediaPojo;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 28.07.2012
 */
public class RendererListboxProjetosUsuario implements ListitemRenderer<ListMediaPojo> {

    /**
     * @see ListitemRenderer#render(Listitem, Object, int)
     */
    public void render(Listitem item, ListMediaPojo data, int index) throws Exception {
        item.setValue(data);
        item.setSclass("itemClick");
        item.appendChild(new Listcell(Labels.getLabel(data.getTipoMedia())));
        item.appendChild(new Listcell(data.getNomePrincipal()));
        item.appendChild(new Listcell(data.getRanking().setScale(2, 4).toString()));
        Date dataInicio = data.getDataInicioProjeto();
        Date dataFim = data.getDataFimProjeto();
        item.appendChild(new Listcell(Labels.getLabel(data.getStatus().getPrefixo())));
        item.appendChild(new Listcell(DataUtils.getDiferencaEntreDatas(dataInicio, dataFim)));
        item.addEventListener(Events.ON_CLICK, ViewHandler.redirectID(MapConstantes.PARAM_ID_MEDIA,
                NavigateEnum.PAGE_DETALHE_MIDIA, data.getId(), NavigateEnum.PAGE_USER_MIDIA));
    }
}
