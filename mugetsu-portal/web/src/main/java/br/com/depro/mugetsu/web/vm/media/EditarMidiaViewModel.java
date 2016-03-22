package br.com.depro.mugetsu.web.vm.media;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.Localidade;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.MediaTitulo;
import br.com.depro.mugetsu.negocio.service.LocalidadeService;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.NavigateHandler;
import br.com.depro.mugetsu.web.utils.ViewHandler;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 */
public class EditarMidiaViewModel extends BaseViewModel {

    /** Numero de serie da classe */
    private static final long serialVersionUID = -8390254906698866431L;
    private static final String SUFIX_NOME_MIDIA = "nomeMidia-";
    private static final String SUFIX_PRINCIPAL = "principal-";
    private static final String SUFIX_IDIOMA = "idioma-";
    @Wire(value = ".rowTitulo")
    private List<Div> divsTitulos;
    @TypezeroBean
    private MediaService midiaService;
    @TypezeroBean
    private LocalidadeService localidadeService;
    private Media midia;
    private List<Localidade> localidades;

    @Init
    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    @Override
    protected void doAlways() throws ApplicationException {
        if (getMidia() == null && ViewHandler.getFromSession(MapConstantes.PARAM_ID_MEDIA) != null) {
            Long id = (Long) ViewHandler.getFromSession(MapConstantes.PARAM_ID_MEDIA);
            setMidia(midiaService.buscarPorId(id));
        }

        if (CollectionUtils.isEmpty(getLocalidades())) {
            setLocalidades(localidadeService.listarTodosIdiomas());
        }
    }

    @Override
    protected void doWithSession() throws ApplicationException {
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    @Command
    public void salvar() {
        for (MediaTitulo titulo : getMidia().getTitulos()) {
            Long idTitulo = titulo.getId();
            for (Div div : divsTitulos) {
                String nome = ((Textbox) div.getFellow(SUFIX_NOME_MIDIA + idTitulo)).getValue();
                Boolean principal = ((Checkbox) div.getFellow(SUFIX_PRINCIPAL + idTitulo)).isChecked();
                Combobox combobox = ((Combobox) div.getFellow(SUFIX_IDIOMA + idTitulo));
                Localidade localidade = null;
                if (combobox.getSelectedItem() != null) {
                    localidade = (Localidade) combobox.getSelectedItem().getValue();
                }
                titulo.setNome(nome);
                titulo.setPrincipal(principal);
                titulo.setLocalidade(localidade);
            }
        }
        try {
            midiaService.atualizar(midia);
        } catch (ApplicationException exp) {
        }
        voltarTelaAnterior();
    }

    /**
     * Volta para tela anterior
     */
    @Command
    public void voltarTelaAnterior() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(MapConstantes.PARAM_ID_MEDIA, getMidia().getId());
        NavigateHandler.voltar(params);
    }

    /**
     * @return the midia
     */
    public Media getMidia() {
        return midia;
    }

    /**
     * @param midia the midia to set
     */
    public void setMidia(Media midia) {
        this.midia = midia;
    }

    /**
     * @return the localidades
     */
    public List<Localidade> getLocalidades() {
        return localidades;
    }

    /**
     * @param localidades the localidades to set
     */
    public void setLocalidades(List<Localidade> localidades) {
        this.localidades = localidades;
    }
}
