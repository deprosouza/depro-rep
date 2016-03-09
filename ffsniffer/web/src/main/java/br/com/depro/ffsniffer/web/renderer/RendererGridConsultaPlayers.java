package br.com.depro.ffsniffer.web.renderer;

import br.com.depro.ffsniffer.model.Player;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 01.04.2015
 */
public class RendererGridConsultaPlayers implements RowRenderer<Player> {

    public void render(Row row, Player data, int index) throws Exception {
        Detail detail = new Detail();
        detail.setParent(row);
        detail.setMold("default");
        detail.setOpen(false);
        new Label(data.getId().toString()).setParent(detail);
        new Label(data.getIdLodestone()).setParent(detail);
        new Label(data.getServer().name()).setParent(detail);
    }
    
}
