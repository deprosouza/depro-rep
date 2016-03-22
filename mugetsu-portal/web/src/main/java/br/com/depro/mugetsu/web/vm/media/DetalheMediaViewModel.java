package br.com.depro.mugetsu.web.vm.media;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Toolbarbutton;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.carga.dmu.service.ImportarDMUService;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.negocio.service.media.MediaService;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
import br.com.depro.mugetsu.web.composite.ListConteudo;
import br.com.depro.mugetsu.web.utils.MapConstantes;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.NavigateHandler;
import br.com.depro.mugetsu.web.utils.TipoEvento;
import br.com.depro.mugetsu.web.utils.ViewHandler;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 * @version $Id$
 */
public class DetalheMediaViewModel extends BaseViewModel {

	/** Numero serial da classe */
	private static final long serialVersionUID = -97097702453724352L;
	@Wire
    private Toolbarbutton adicionarProjeto;
    @Wire
    private ListConteudo lbConteudo;
    @Wire
    private Button atualziarMedia;
    @TypezeroBean
    private MediaService mediaService;
    @TypezeroBean
    private ProjetoService projetoService;
    @TypezeroBean
    private ImportarDMUService dmuService;
    private Media media;
    private Projeto projeto;
    private boolean projetoExistente;

    @Init
    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    /**
     * metodo executado sempre para qualquer cenario
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    @Override
    protected void doAlways() throws ApplicationException {
        if (media == null && ViewHandler.getFromSession(MapConstantes.PARAM_ID_MEDIA) != null) {
            Long id = (Long) ViewHandler.getFromSessionAndRemove(MapConstantes.PARAM_ID_MEDIA);
            setMedia(mediaService.buscarPorId(id));
        }
    }

    /**
     * Metodo que configura visualizacao se usuario estiver logado
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    @Override
    protected void doWithSession() throws ApplicationException {
        if (getProjeto() == null) {
            setProjeto(projetoService.buscarPorCredencialEMidia(getCredenciaisUsuario(), getMedia()));
        }
    }

    /**
     * Metodo que configura visualizacao se usuario estiver logado
     *
     * @throws ApplicationException Caso algum erro ocorra
     */
    @Override
    protected void doWithoutSession() throws ApplicationException {
        setProjetoExistente(false);
    }

    /**
     * Volta para tela anterior
     */
    @Command("voltar")
    public void voltarTelaAnterior() {
        NavigateHandler.voltar();
    }

    /**
     * Adciona media nas medias do usuario
     */
    @Command
    public void adicionarMinhasMedias() {
        try {
            this.projetoService.inserir(getCredenciaisUsuario(), getMedia());
            doWithSession();
            this.adicionarProjeto.detach();
            this.lbConteudo.setModel(getMedia().getConteudos());
        } catch (ApplicationException e) {
            ViewHandler.showMessageErro(e);
        }
    }
    
    @Command
    public void atualuzarMediaOrigem() {
    	try {
    		dmuService.atualizarDadosMedia(getMedia());
    		
    		ViewHandler.putInSession(MapConstantes.PARAM_ID_MEDIA, getMedia().getId());
        	NavigateHandler.navigate(NavigateEnum.PAGE_DETALHE_MIDIA);
    	} catch (ApplicationException aexp) {
    		
    	}
    }
    
    /**
     * Redireciona para tela de edicao de midia
     */
    @Command
    public void editarMidia() throws ApplicationException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(MapConstantes.PARAM_ID_MEDIA, getMedia().getId());
        NavigateHandler.navigate(NavigateEnum.PAGE_EDITAR_MIDIA, param);
    }

    /**
     *
     * @return @throws IOException
     * @throws ApplicationException
     * @throws ApplicationException
     */
    public AImage getImagem() throws ApplicationException, IOException {
        return ViewHandler.getImage(TipoEvento.EXIBICAO, this.getMedia().getPathImagem());
    }

    /**
     *
     * @return
     */
    public Media getMedia() {
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
     *
     * @return
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     *
     * @param projeto
     */
    public void setProjeto(Projeto projeto) {
        setProjetoExistente(projeto != null);
        this.projeto = projeto;
    }

    /**
     *
     * @return
     */
    public boolean isProjetoExistente() {
        return projetoExistente;
    }

    /**
     *
     * @param projetoExistente
     */
    public void setProjetoExistente(boolean projetoExistente) {
        this.projetoExistente = projetoExistente;
    }
}
