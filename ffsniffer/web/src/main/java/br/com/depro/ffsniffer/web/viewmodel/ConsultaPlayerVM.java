package br.com.depro.ffsniffer.web.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import br.com.depro.ffsniffer.core.service.PlayerService;
import br.com.depro.ffsniffer.model.Player;
import br.com.depro.ffsniffer.web.renderer.RendererGridConsultaPlayers;
import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 24.03.2015
 */
public class ConsultaPlayerVM extends BaseViewModel {

    /** Numero serial da classe */
    private static final long serialVersionUID = 1895436517683926440L;
    @TypezeroBean
    private PlayerService playerService;
    @Wire
    private Grid gridPlayers;
    private List<Player> listCharacters;

    @Init
    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    @Command("buscarPlayers")
    public void buscarPlayers(@BindingParam("nome") String nome) {
//    	gridPlayers.setRowRenderer(new RendererGridConsultaPlayers());
        gridPlayers.setModel(new ListModelList<Player>(playerService.burcasrPorNome(nome)));
    }

    @Override
    protected void doAlways() throws ApplicationException {
    }

    @Override
    protected void doWithSession() throws ApplicationException {
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     * @return the listCharacters
     */
    public List<Player> getListCharacters() {
        return listCharacters;
    }

    /**
     * @param listCharacters the listCharacters to set
     */
    public void setListCharacters(List<Player> listCharacters) {
        this.listCharacters = listCharacters;
    }
}
