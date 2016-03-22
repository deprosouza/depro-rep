package br.com.depro.mugetsu.web.vm.media;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.web.composite.ListMedia;
import br.com.depro.mugetsu.web.composite.filter.FiltroListMedia;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.ViewHandler;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 * @version $Id$
 */
public class ConsultaMidiaPublicaViewModel extends BaseViewModel {

    /** Numero serial da classe */
	private static final long serialVersionUID = -6072129803201107454L;
	@Wire
    private Div divLista;
    @Wire(".formatos")
    private List<A> formatosSelecaoFiltro;
    private List<FormatoMedia> formatos;

    @Init
    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    @Override
    protected void doAlways() throws ApplicationException {
        if (CollectionUtils.isEmpty(getFormatos())) {
            setFormatos(FormatoMedia.getListValues());
        }

        FiltroListMedia filtro = (FiltroListMedia) ViewHandler.getFromSession(MapConstantes.PARAM_FILTRO_CONSULTA_LIST_MEDIA);
        if (filtro != null && filtro.isValid()) {
            atribuiFormato(filtro.getFormatoSelecao(), false);
        }
    }

    @Override
    protected void doWithSession() throws ApplicationException {
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     *
     * @param formato
     */
    @Command
    public void selecionaForamtoMedia(@BindingParam("formatoMedia") FormatoMedia formatoMedia) {
        atribuiFormato(formatoMedia, true);
    }

    /**
     *
     * @param resetFiltro
     * @param toolbarbutton
     * @throws ComponentNotFoundException
     */
    private void atribuiFormato(FormatoMedia formatoSelecionado, boolean resetFiltro) throws ComponentNotFoundException {
        if (!CollectionUtils.isEmpty(formatosSelecaoFiltro) && formatoSelecionado != null) {
            for (A link : formatosSelecaoFiltro) {
                if (link.getSclass().indexOf(formatoSelecionado.name()) > 0) {
                    link.setDisabled(true);
                    link.setSclass(link.getSclass() + " active");
                } else {
                    link.setDisabled(false);
                    link.setSclass(link.getSclass().replace("active", ""));
                }
            }
            if (this.divLista.getChildren().size() > 0) {
                ListMedia listmedia = (ListMedia) divLista.getFellow("lbListamedia");
                listmedia.detach();
            }
            ListMedia listMedia = new ListMedia();
            listMedia.setReset(resetFiltro);
            listMedia.setId("lbListamedia");
            listMedia.setLayout(ListMedia.Layout.MEDIATITULO_C);
            listMedia.setFormatoMedia(formatoSelecionado);
            this.divLista.appendChild(listMedia);
        }
    }

    /**
     *
     * @return
     */
    public List<FormatoMedia> getFormatos() {
        return formatos;
    }

    /**
     *
     * @param formatos
     */
    public void setFormatos(List<FormatoMedia> formatos) {
        this.formatos = formatos;
    }
}
