package br.com.depro.mugetsu.web.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.media.Genero;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.negocio.service.PerfilService;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
import br.com.depro.mugetsu.web.composite.filter.FiltroListMedia;
import br.com.depro.mugetsu.web.composite.pojo.ListMediaPojo;
import br.com.depro.mugetsu.web.renderer.RendererListboxMediaConsulta;
import br.com.depro.mugetsu.web.renderer.RendererListboxMediaRecomentada;
import br.com.depro.mugetsu.web.renderer.RendererListboxProjetosUsuario;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.SessionUtils;
import br.com.depro.mugetsu.web.utils.ViewHandler;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.07.2012
 * @version 2.0 - Ajustes para retorna com a ultima consulta valida e correcao
 * de bug no filtro de formatos - 11.08.2012
 */
public class ListMedia extends Div implements IdSpace {

    /**
     * Enum que especifica templates disponiveis
     *
     * @author rsouza
     * @version 1.0 - Versao Inicial - 26.07.2012
     */
    public enum Layout {

        SIMPLES(""),
        MEDIATITULO_C("headMediaTituloConsulta"),
        MEDIARECOMENDADA("headMediaRecomentada"),
        PROJETOUSUARIO("headProjetoUsuario");
        private String sclassLayout;

        /**
         * Indica se layout selecionado e de projeto de usuario
         *
         * @return
         */
        public boolean isProjetoUsuario() {
            return this.equals(PROJETOUSUARIO);
        }

        /**
         * Construtor
         *
         * @param sclassLayout
         */
        Layout(String sclassLayout) {
            this.sclassLayout = sclassLayout;
        }

        /**
         *
         * @return
         */
        public String getSclassLayout() {
            return sclassLayout;
        }
    }

    /**
     * Enum que especifica templates disponiveis
     *
     * @author rsouza
     * @version 1.0 - Versao Inicial - 11.08.2012
     */
    public enum Formato {

        MEDIA, ANIME, DRAMA;
    }
    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = 1782761655579204389L;
    @Wire
    private Listbox lbMedia;
    @Wire("listheader")
    private List<Listheader> headers;
    @Wire
    private Vlayout vlFormatoMedia;
    @Wire
    private Vlayout vlFormatoAnime;
    @Wire
    private Vlayout vlFormatoDorama;
    @Wire
    private Textbox txtFiltroNome;
    @Wire
    private Checkbox chkNomeImagem;
    @Wire
    private Div msgErro;
    @Wire
    private Hlayout boxLetras;
    @Wire
    private Vlayout vlGeneros;
    @Wire("checkbox")
    private List<Checkbox> checks;
    @Wire
    private Groupbox gblbMidia;
    @Wire
    private Groupbox groupFormatos;
    private ListModelList<ListMediaPojo> model;
    private Layout layout;
    private FiltroListMedia filtro;
    private FormatoMedia formatoMedia;
    private boolean reset = false;
    
    /**
     * Construtor padrao da classe
     */
    public ListMedia() {
        Executions.createComponents(NavigateEnum.COMPISITE_LISTA_MIDIA.getUri(), this, null);
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this, this);
        this.lbMedia.getPagingChild().setMold("os");
    }

    /**
     * Seta medias que serão listadas
     *
     * @param dados
     */
    @SuppressWarnings("rawtypes")
    public void setModel(List dados) {
        this.model = new ListModelList<ListMediaPojo>(ListMediaPojo.parse(dados));
        for (Listheader head : headers) {
            if (!head.getSclass().equals(layout.getSclassLayout())) {
                head.detach();
            }
        }
        if (layout.equals(Layout.PROJETOUSUARIO)) {
            this.lbMedia.setItemRenderer(new RendererListboxProjetosUsuario());
        } else if (layout.equals(Layout.MEDIATITULO_C)) {
            this.lbMedia.setItemRenderer(new RendererListboxMediaConsulta());
        } else if (this.layout.equals(Layout.MEDIARECOMENDADA)) {
            this.lbMedia.setItemRenderer(new RendererListboxMediaRecomentada());
        }
        this.filtro.setLayout(this.layout);
        this.aplicarFiltros();
        
        Integer pagina = (Integer)ViewHandler.getFromSessionAndRemove(MapConstantes.PARAM_PAGE_LIST_MEDIA);
        if (pagina != null) {
        	this.lbMedia.setActivePage(pagina);
        } else {
        	this.lbMedia.setActivePage(0);
        }
    }
    
    /**
     * Filtra media por letra
     *
     * @param event
     */
    @Listen("onClick = .btnLetra")
    public void pesquizarMedia(Event event) {
    	limparCampoConsulta();
    	ViewHandler.removeFromSession(MapConstantes.PARAM_PAGE_LIST_MEDIA);
        Button button = (Button) event.getTarget();
        for (Component component : boxLetras.getChildren()) {
            ((Button) component).setDisabled(false);
            ((Button) component).setClass("btn btnLetra btn-default");
        }
        button.setDisabled(true);
        button.setClass("btn btnLetra btn-danger");
        String letra = button.getLabel();
        this.filtro.setLetra(letra);
        this.consultarPorLetra(letra);
    }

    /**
     * Marca botao selecionado pela letra
     *
     * @param letra
     */
    private void marcarBotao(String letra) {
        for (Component component : boxLetras.getChildren()) {
            Button button = (Button) component;
            if (button.getLabel().equals(letra)) {
                button.setDisabled(true);
                button.setClass("btn btnLetra btn-danger");
            } else {
                button.setDisabled(false);
                button.setClass("btn btnLetra btn-default");
            }
        }
    }

    /**
     * Consulta medias pela primeira letra
     *
     * @param letra
     */
    @SuppressWarnings("rawtypes")
    private void consultarPorLetra(String letra) {
        try {
            List rawModel = null;
            if (layout.equals(Layout.MEDIATITULO_C)) {
                MediaService service = (MediaService) MugetsuSpringUtils.getBean(MediaService.class);
                if (getFormatoMedia() != null) {
                    rawModel = service.listarMediasPorPrimeiraLetraEFormatoMedia(letra, chkNomeImagem.isChecked(), getFormatoMedia());
                } else {
                    rawModel = service.listarMediasPorPrimeiraLetra(letra, chkNomeImagem.isChecked());
                }
                this.filtro.setFormatoSelecao(getFormatoMedia());
            } else if (this.layout.equals(Layout.PROJETOUSUARIO)) {
                ProjetoService service = (ProjetoService) MugetsuSpringUtils.getBean(ProjetoService.class);
                rawModel = service.listarProjetosPorUsuarioEPrimeiraLetra(SessionUtils.getCredenciais(), letra);
            } else if (this.layout.equals(Layout.MEDIARECOMENDADA)) {
                PerfilService service = (PerfilService) MugetsuSpringUtils.getBean(PerfilService.class);
                rawModel = service.listraIndicacoesUsuario(SessionUtils.getCredenciais());
            }
            this.setModel(rawModel);
        } catch (ApplicationException nexp) {
            ViewHandler.showMessageErro(nexp);
        }
    }

    /**
     * Evento de selecao do formato da media
     */
    @Listen("onClick = .checkMedia")
    public void selecaoTipoMedia() {
        this.verificarSelecaoFormatoMedia();
        this.aplicarFiltros();
    }

    @Listen("onClick = .limpar")
    public void limparCampoConsulta() {
        this.txtFiltroNome.setValue("");
        this.chkNomeImagem.setChecked(false);
        this.aplicarFiltros();
    }
    
    @Listen("onClick = .filtar; onOK = #txtFiltroNome")
    public void buscar() {
    	verificaSeAplicaFiltroImagem();
    	aplicarFiltros();
    }

    /**
     * Evento de click em qualquer componente com o estilo de filtrar
     */
    public void aplicarFiltros() {
        List<ListMediaPojo> listaIntermediaria = new ArrayList<ListMediaPojo>();
        if (this.model != null) {
            listaIntermediaria.addAll(filtrarPorNome(model.getInnerList(), txtFiltroNome.getValue()));

            if (getFormatoMedia() == null) {
                filtrarFormatos(listaIntermediaria, vlFormatoMedia, Formato.MEDIA);
                filtrarFormatos(listaIntermediaria, this.vlFormatoAnime, Formato.ANIME);
                filtrarFormatos(listaIntermediaria, this.vlFormatoDorama, Formato.DRAMA);
            } else if (getFormatoMedia().equals(FormatoMedia.ANIME)) {
                filtrarFormatos(listaIntermediaria, this.vlFormatoAnime, Formato.ANIME);
            } else if (getFormatoMedia().equals(FormatoMedia.DORAMA)) {
                filtrarFormatos(listaIntermediaria, this.vlFormatoDorama, Formato.DRAMA);
            }

            filtrarGenero(listaIntermediaria);
        }

        if (!listaIntermediaria.isEmpty()) {
            this.gblbMidia.setVisible(true);
            this.msgErro.setVisible(false);
        } else {
            this.gblbMidia.setVisible(false);
            this.msgErro.setVisible(true);
        }
        this.filtro.setValid(true);
        ViewHandler.putInSession(MapConstantes.PARAM_FILTRO_CONSULTA_LIST_MEDIA, this.filtro);
        this.lbMedia.setModel(new ListModelList<ListMediaPojo>(listaIntermediaria));
    }
    
    /**
     * 
     */
    private void verificaSeAplicaFiltroImagem() {
    	if (chkNomeImagem.isChecked()) {
    		marcarBotao(null);
    		this.consultarPorLetra(txtFiltroNome.getValue());
    		this.filtro.setPalavra(txtFiltroNome.getValue());
    		this.filtro.setConsultaImagem(true);
    	} else {
    		this.filtro.setConsultaImagem(false);
    		marcarBotao(filtro.getLetra());
    	}
    }

    /**
     * Filtra titilos pelos nomes
     *
     * @param dados
     * @param palavra
     * @return Lista de pojos filtrada
     */
    private List<ListMediaPojo> filtrarPorNome(List<ListMediaPojo> dados, String palavra) {
    	if (!chkNomeImagem.isChecked()) {
	        List<ListMediaPojo> retorno = new ArrayList<ListMediaPojo>();
	        for (ListMediaPojo dado : dados) {
	            if (dado.getNomePrincipal().toLowerCase().indexOf(palavra.toLowerCase()) != -1) {
	                retorno.add(dado);
	            }
	        }
	        this.filtro.setPalavra(palavra);
	        return retorno;
    	} else {
    		return dados;
    	}
    	
    }

    /**
     * Filtra os titulso pelo formato da media ou do anime
     *
     * @param dados
     * @param component
     * @param isFormatoMedia
     */
    private void filtrarFormatos(List<ListMediaPojo> dados, Component component, Formato formato) {
        List<String> prefixoFormatos = obterFormatos(component);
        if (!prefixoFormatos.isEmpty()) {
            for (Iterator<ListMediaPojo> iterator = dados.iterator(); iterator.hasNext();) {
                ListMediaPojo dado = (ListMediaPojo) iterator.next();
                boolean isRemoverLista = true;
                String prefixoFormato = null;
                if (formato.equals(Formato.MEDIA)) {
                    prefixoFormato = dado.getFormatoMedia().getPrefixo();
                    this.filtro.setFormatoMedias(prefixoFormatos);
                } else if (formato.equals(Formato.ANIME) && dado.getFormatoMedia().equals(FormatoMedia.ANIME)) {
                    prefixoFormato = dado.getFormatoAnime().getPrefixo();
                    this.filtro.setFormatoAnimes(prefixoFormatos);
                } else if (formato.equals(Formato.DRAMA) && dado.getFormatoMedia().equals(FormatoMedia.DORAMA)) {
                    prefixoFormato = dado.getFormatoDorama().getPrefixo();
                    this.filtro.setFormatoDoramas(prefixoFormatos);
                }
                if (formato.equals(Formato.MEDIA) || prefixoFormato != null) {
                    for (String idFormatoSelecionado : prefixoFormatos) {
                        if (prefixoFormato.equals(idFormatoSelecionado)) {
                            isRemoverLista = false;
                            break;
                        }
                    }
                    if (isRemoverLista) {
                        iterator.remove();
                    }
                }
            }
        } else {
            if (formato.equals(Formato.MEDIA)) {
                this.filtro.setFormatoMedias(prefixoFormatos);
            } else if (formato.equals(Formato.ANIME)) {
                this.filtro.setFormatoAnimes(prefixoFormatos);
            } else if (formato.equals(Formato.DRAMA)) {
                this.filtro.setFormatoDoramas(prefixoFormatos);
            }
        }
    }

    /**
     * Filtar lista de medias por genero
     *
     * @param dados
     */
    private void filtrarGenero(List<ListMediaPojo> dados) {
        List<String> generos = this.obterGeneros();
        int quantidadeGeneros = generos.size();
        int quantidadeCompativel = 0;
        if (!generos.isEmpty()) {
            for (Iterator<ListMediaPojo> iterator = dados.iterator(); iterator
                    .hasNext();) {
                ListMediaPojo dado = iterator.next();
                quantidadeCompativel = 0;
                for (Genero geMedia : dado.getGeneros()) {
                    for (String genero : generos) {
                        if (geMedia.getPrefixo().equals(genero)) {
                            quantidadeCompativel++;
                            break;
                        }
                    }
                }
                if (quantidadeCompativel != quantidadeGeneros) {
                    iterator.remove();
                }
            }
        }
        this.filtro.setGeneros(generos);
    }

    /**
     *
     * @param component
     * @return
     */
    private List<String> obterFormatos(Component component) {
        List<String> prefixoFormatos = new ArrayList<String>();
        List<Component> checkboxComponents = component.getChildren();
        for (Component checkboxComponent : checkboxComponents) {
            Checkbox checkbox = (Checkbox) checkboxComponent;
            if (checkbox.isChecked()) {
                prefixoFormatos.add(String.valueOf(checkbox.getValue()));
            }
        }
        return prefixoFormatos;
    }

    /**
     * Obtem generos selecionados
     *
     * @return
     */
    private List<String> obterGeneros() {
        List<String> generos = new ArrayList<String>();
        for (Component component : vlGeneros.getChildren()) {
            Checkbox checkbox = (Checkbox) component;
            if (checkbox.isChecked()) {
                generos.add(String.valueOf(checkbox.getValue()));
            }
        }
        return generos;
    }

    /**
     * Executa filtro da consulta anterior
     */
    private void executarFiltroAnterior() {
        this.setFormatoMedia(filtro.getFormatoSelecao());
        this.autoCheck(this.filtro.getFormatoMedias());
        this.autoCheck(this.filtro.getFormatoAnimes());
        this.autoCheck(this.filtro.getFormatoDoramas());
        this.autoCheck(this.filtro.getGeneros());
        this.aplicarConfiguracaoTela();
        this.desabilitarBotao();
        this.txtFiltroNome.setValue(this.filtro.getPalavra());
        this.layout = filtro.getLayout();
        this.marcarBotao(this.filtro.getLetra());
        verificaSeAplicaFiltroImagem();
        if (!chkNomeImagem.isChecked()) {
        	this.consultarPorLetra(this.filtro.getLetra());
        }
    }

    /**
     * Ativa / Desativa botoes
     */
    private void desabilitarBotao() {
        for (Component component : boxLetras.getChildren()) {
            Button button = (Button) component;
            if (button.getLabel().equals(this.filtro.getLetra())) {
                button.setDisabled(true);
            } else {
                button.setDisabled(false);
            }
        }
    }

    /**
     * Método que checara formato de medias.
     */
    private void autoCheck(List<String> prefixos) {
        for (Component comp : this.checks) {
            if (comp instanceof Checkbox) {
                Checkbox check = (Checkbox) comp;
                for (String prefixo : prefixos) {
                    if (check.getValue().equals(prefixo)) {
                        check.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    private void verificarSelecaoFormatoMedia() {
        List<Checkbox> checkboxs = new ArrayList<Checkbox>();
        if (getFormatoMedia() == null) {
            checkboxs.add((Checkbox) this.vlFormatoMedia.getFellow("fm" + FormatoMedia.ANIME.getPrefixo()));
            checkboxs.add((Checkbox) this.vlFormatoMedia.getFellow("fm" + FormatoMedia.DORAMA.getPrefixo()));
        } else if (FormatoMedia.ANIME.equals(getFormatoMedia())) {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(FormatoMedia.ANIME.getPrefixo());
            checkbox.setChecked(true);
            checkboxs.add(checkbox);
        } else if (FormatoMedia.DORAMA.equals(getFormatoMedia())) {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(FormatoMedia.DORAMA.getPrefixo());
            checkbox.setChecked(true);
            checkboxs.add(checkbox);
        }
        for (Checkbox checkbox : checkboxs) {
            List<Component> checks = new ArrayList<Component>();
            if (checkbox.getValue().equals(FormatoMedia.ANIME.getPrefixo())) {
                checks = vlFormatoAnime.getChildren();
            } else if (checkbox.getValue().equals(
                    FormatoMedia.DORAMA.getPrefixo())) {
                checks = vlFormatoDorama.getChildren();
            }

            for (Component component : checks) {
                if (checkbox.isChecked()) {
                    ((Checkbox) component).setDisabled(false);
                } else {
                    ((Checkbox) component).setDisabled(true);
                    ((Checkbox) component).setChecked(false);
                }
            }
        }
    }

    /**
     * Realiza a execucao dos filtros
     *
     */
    private void executarFiltro() {
        this.filtro = (FiltroListMedia) ViewHandler.getFromSession(MapConstantes.PARAM_FILTRO_CONSULTA_LIST_MEDIA);
        if (filtro != null && filtro.isValid() && filtro.getLayout().equals(layout) && !reset) {
            executarFiltroAnterior();
        } else {
            filtro = new FiltroListMedia();
        }
    }

    /**
     * Renderisa componentes baseado nas atributos passados
     */
    private void aplicarConfiguracaoTela() {
        if (!getLayout().isProjetoUsuario()) {
            if (getFormatoMedia() == null) {
                this.vlFormatoAnime.detach();
                this.vlFormatoDorama.detach();
                this.vlFormatoMedia.detach();
            } else {
                if (FormatoMedia.ANIME.equals(getFormatoMedia())) {
                    this.vlFormatoDorama.detach();
                    this.vlFormatoMedia.detach();
                } else if (FormatoMedia.DORAMA.equals(getFormatoMedia())) {
                    this.vlFormatoAnime.detach();
                    this.vlFormatoMedia.detach();
                } else {
                    this.groupFormatos.detach();
                }
            }
        }
        this.verificarSelecaoFormatoMedia();
        if (this.filtro.isConsultaImagem()) {
        	chkNomeImagem.setChecked(true);
        }
    }

    /**
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
        this.executarFiltro();
    }

    /**
     * Seta tamanho de cada pagina
     *
     * @param tamanho
     */
    public void setTamanhoPagina(int tamanho) {
        this.lbMedia.setPageSize(tamanho);
    }

    /**
     * @return the formatoMedia
     */
    public FormatoMedia getFormatoMedia() {
        return formatoMedia;
    }

    /**
     * @param formatoMedia the formatoMedia to set
     */
    public void setFormatoMedia(FormatoMedia formatoMedia) {
        this.formatoMedia = formatoMedia;
        this.aplicarConfiguracaoTela();
    }

    /**
     * @return the reset
     */
    public boolean isReset() {
        return reset;
    }

    /**
     * @param reset the reset to set
     */
    public void setReset(boolean reset) {
        this.reset = reset;
    }
}
