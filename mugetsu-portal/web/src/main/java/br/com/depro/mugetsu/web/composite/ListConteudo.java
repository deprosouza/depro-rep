package br.com.depro.mugetsu.web.composite;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.media.Conteudo;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.negocio.service.media.ConteudoService;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
import br.com.depro.mugetsu.web.composite.pojo.ListConteudoPojo;
import br.com.depro.mugetsu.web.controllers.SessionController;
import br.com.depro.mugetsu.web.renderer.RendererListboxConteudoCompleto;
import br.com.depro.mugetsu.web.renderer.RendererListboxConteudoSimples;
import br.com.depro.mugetsu.web.security.Rights;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.SessionUtils;
import br.com.depro.mugetsu.web.utils.ViewHandler;
import br.com.depro.mugetsu.web.vm.media.DetalheMediaViewModel;
import bsh.org.objectweb.asm.Label;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.07.2012
 */
public class ListConteudo extends Div implements IdSpace, AfterCompose {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = -6419584306210107303L;

    @Override
    public void afterCompose() {
        Selectors.wireComponents(this, this, false);
        Selectors.wireEventListeners(this, this);
        this.validarComponente();
        this.lbConteudos.getPagingChild().setMold("os");
        Selectors.wireEventListeners(lbConteudos, this);
    }

    /**
     *
     * @author rsouza
     * @version 1.0 - Versao Inicial - 30.07.2012
     */
    public enum Layout {

        SIMPLES("headerSimples"),
        COMPLETO("headerConteudo");
        private String sclassLayout;

        private Layout(String sclassLayout) {
            this.sclassLayout = sclassLayout;
        }

        /**
         * @return the sclassLayout
         */
        public String getSclassLayout() {
            return sclassLayout;
        }

        /**
         * @param sclassLayout the sclassLayout to set
         */
        public void setSclassLayout(String sclassLayout) {
            this.sclassLayout = sclassLayout;
        }
    }
    @Wire
    private Listbox lbConteudos;
    @Wire
    private Label lConteudoSize;
    @Wire("listheader")
    private List<Listheader> listheaders;
    @Wire
    private Caption capList;
    @Wire
    private Button btnAddConteudo;
    @Wire
    private Button btnAddUmConteudo;
    @Wire
    private Div divToolbar;
    private ListModelList<ListConteudoPojo> model;
    private Layout layout;
    private ConteudoService conteudoService;
    private ProjetoService projetoService;
    private MediaService mediaService;
    private Projeto projeto;
    private Long mediaId;
    private Media media;

    /**
     * Construtor do componente
     */
    public ListConteudo() {
        Executions.createComponents(NavigateEnum.COMPOSITE_LISTA_CONTEUDO.getUri(), this, null);
    }

    @Listen("onClick = #btnAddConteudo")
    public void adicionarConteudo() {
    }

    @Listen("onClick = #btnAddUmConteudo")
    public void btnAddUmConteudo() {
        try {
            this.getMediaService().inserirConteudoPorMedia(getMedia(), 1);
            this.setModel(getMedia().getConteudos());
        } catch (ApplicationException nexp) {
            ViewHandler.showMessageErro(nexp);
        }
    }

    @Listen("onClick = #btnCompleto")
    public void btnCompleto() {
        this.atualizarCoteudoProjeto(Boolean.TRUE, Boolean.TRUE);
    }

    @Listen("onClick = #btnCompletarBaixado")
    public void btnBaixados() {
        this.atualizarCoteudoProjeto(Boolean.TRUE, null);
    }

    @Listen("onClick = #btnCompletarAssistido")
    public void btnAssistidos() {
        this.atualizarCoteudoProjeto(null, Boolean.TRUE);
    }

    @Listen("onClick = .alterarStatusFiller")
    public void alterarFiller() {
        Messagebox.show("TEste");
    }

    /**
     * @param model the model to set
     */
    public void setModel(List<Conteudo> dados) {
        this.model = new ListModelList<ListConteudoPojo>(ListConteudoPojo.parse(dados, projeto));
        this.validarHeaderLista();
        if (this.layout.equals(Layout.COMPLETO)) {
            this.lbConteudos.setItemRenderer(new RendererListboxConteudoCompleto());
        } else if (this.layout.equals((Layout.SIMPLES))) {
            this.lbConteudos.setItemRenderer(new RendererListboxConteudoSimples());
        }

        this.capList.setLabel("Conteudo: " + this.model.size());
        this.lbConteudos.setModel(this.model);
        Selectors.wireEventListeners(lbConteudos, DetalheMediaViewModel.class);
    }

    /**
     * @param conteudoFromId the conteudoFromId to set
     */
    public void setConteudoFromId(Long conteudoFromId) {
        try {
            this.mediaId = conteudoFromId;
            this.projeto = getProjetoService().buscarPorCredencialEIdMedia(SessionUtils.getCredenciais(), conteudoFromId);
            this.setModel(this.getConteudoService().listarConteudoPorMediaId(conteudoFromId));
        } catch (ApplicationException nexp) {
            ViewHandler.showMessageErro(nexp);
        }
    }

    /**
     * @param conteudoFromMedia the conteudoFromMedia to set
     */
    public void setConteudoFromMedia(Media conteudoFromMedia) {
        try {
            setMedia(conteudoFromMedia);
            this.projeto = getProjetoService().buscarPorCredencialEMidia(SessionController.obterUsuarioSessao(), getMedia());
            this.setModel(conteudoFromMedia.getConteudos());
        } catch (ApplicationException nexp) {
            ViewHandler.showMessageErro(nexp);
        }
    }

    /**
     * Prepara o projeto caso ele nao exista
     */
    private void prepararCriacaoProjeto() {
        if (projeto == null) {
            try {
                projeto = new Projeto();
                getProjetoService().inserir(SessionUtils.getCredenciais(), getMedia(), projeto);
                this.setModel(getMedia().getConteudos());
            } catch (ApplicationException nexp) {
                ViewHandler.showMessageErro(nexp);
            }
        }
    }

    /**
     * Atualiza dados sobre a comteudo do projeto
     *
     * @param isSetaBaixados
     * @param isSetaAssistidos
     */
    private void atualizarCoteudoProjeto(Boolean isSetaBaixados, Boolean isSetaAssistidos) {
        if (getModel() != null) {
            try {
                boolean isAllOK = true;
                prepararCriacaoProjeto();
                for (ListConteudoPojo pojo : getModel().getInnerList()) {
                    ProjetoConteudo projetoConteudo = pojo.getProjetoConteudo();

                    if (isSetaBaixados != null && isSetaBaixados) {
                        projetoConteudo.setBaixado(true);
                        projetoConteudo.setDataDownload(new Date());
                    }

                    if (isSetaAssistidos != null && isSetaAssistidos) {
                        projetoConteudo.setAssistido(true);
                        projetoConteudo.setDataVisualizacao(new Date());
                    }

                    isAllOK = isAllOK && projetoConteudo.isAssistido() && projetoConteudo.isBaixado();
                }

                if (isAllOK) {
                    this.projeto.setQuantidadeEpisodios(getModel().getInnerList().size());
                    this.projeto.setDataFinal(new Date());
                    this.projeto.setStatus(Status.COMPLETO);
                }

                getProjetoService().salvar(projeto);
                setModel(media.getConteudos());

            } catch (ApplicationException nexp) {
                ViewHandler.showMessageErro(nexp);
            }
        }
    }

    /**
     * Valida qual cabecalho sera exibito na lista
     */
    private void validarHeaderLista() {
        if (SessionController.isSessionValid()) {
            this.layout = Layout.COMPLETO;
        } else {
            this.layout = Layout.SIMPLES;
        }
        for (Listheader header : listheaders) {
            if (StringUtils.isNotBlank(header.getSclass()) && !header.getSclass().equals(layout.getSclassLayout())) {
                header.detach();
            }
        }
    }

    /**
     *
     */
    private void validarComponente() {
        if (SessionController.isSessionValid()) {
            if (!SessionController.isAllowed(Rights.ADD_CONTEUDO)) {
                this.btnAddConteudo.detach();
                this.btnAddUmConteudo.detach();
            }
        } else {
            this.divToolbar.detach();
        }
    }

    /**
     *
     * @return
     */
    public Media getMedia() throws ApplicationException {
        if (this.media == null) {
            this.media = this.getMediaService().buscarPorId(this.mediaId);
        }
        return media;
    }

    /**
     *
     * @param media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * @return the model
     */
    public ListModelList<ListConteudoPojo> getModel() {
        return model;
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
    }

    /**
     * @return the lbConteudos
     */
    public Listbox getLbConteudos() {
        return lbConteudos;
    }

    /**
     * @param lbConteudos the lbConteudos to set
     */
    public void setLbConteudos(Listbox lbConteudos) {
        this.lbConteudos = lbConteudos;
    }

    /**
     * @return the lConteudoSize
     */
    public Label getlConteudoSize() {
        return lConteudoSize;
    }

    /**
     * @param lConteudoSize the lConteudoSize to set
     */
    public void setlConteudoSize(Label lConteudoSize) {
        this.lConteudoSize = lConteudoSize;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ListModelList<ListConteudoPojo> model) {
        this.model = model;
    }

    /**
     * @return the conteudoService
     */
    public ConteudoService getConteudoService() {
        if (this.conteudoService == null) {
            this.conteudoService = (ConteudoService) MugetsuSpringUtils.getBean(ConteudoService.class);
        }
        return conteudoService;
    }

    /**
     * @return the projetoService
     */
    public ProjetoService getProjetoService() {
        if (this.projetoService == null) {
            this.projetoService = (ProjetoService) MugetsuSpringUtils.getBean(ProjetoService.class);
        }
        return projetoService;
    }

    /**
     * @return the mediaService
     */
    public MediaService getMediaService() {
        if (this.mediaService == null) {
            this.mediaService = (MediaService) MugetsuSpringUtils.getBean(MediaService.class);
        }
        return mediaService;
    }
}
