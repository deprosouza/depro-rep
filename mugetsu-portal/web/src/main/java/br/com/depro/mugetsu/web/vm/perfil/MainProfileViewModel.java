package br.com.depro.mugetsu.web.vm.perfil;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.security.Login;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoConteudoService;
import br.com.depro.mugetsu.negocio.service.security.SessionService;
import br.com.depro.mugetsu.web.utils.NavigateEnum;
import br.com.depro.mugetsu.web.utils.NavigateHandler;
import br.com.depro.mugetsu.web.utils.TipoEvento;
import br.com.depro.mugetsu.web.utils.ViewHandler;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 22.07.2012
 */
public class MainProfileViewModel extends BaseViewModel {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = -4941712029156264584L;
    @Wire
    private Label labelNaoAssistidos;
    @TypezeroBean
    private ProjetoConteudoService projetoConteudoService;
    @TypezeroBean
    private SessionService sessionService;
    private int quantidadeNaoAssistidos;

    @Init
    @Override
    @AfterCompose
    public void initViewModel(@ContextParam(ContextType.VIEW) Component view) {
        super.initViewModel(view);
    }

    @Override
    protected void doAlways() throws ApplicationException {
    }

    @Override
    protected void doWithSession() throws ApplicationException {
        obtemQuantidadeNaoAssistidos();
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     *
     */
    @Command
    public void verMinhasMidas() {
        NavigateHandler.navigate(NavigateEnum.PAGE_USER_MIDIA);
    }

    /**
     *
     */
    @Command
    public void verMinhasProgramacao() {
        ViewHandler.doModal(NavigateEnum.PAGE_USER_MIDIA_PROGRAMACAO.getUri(), null, null);
    }

    /**
     * Atuializa todos os componentes dinamicos do menu lateral
     */
    @Command
    public void obtemQuantidadeNaoAssistidos() {
        if (labelNaoAssistidos != null) {
            setQuantidadeNaoAssistidos(projetoConteudoService.buscarQuantidadeNaoAssistidos(getCredenciaisUsuario()));
            this.labelNaoAssistidos.setValue(String.valueOf(getQuantidadeNaoAssistidos()));
        }
    }

    /**
     * Encerra a sessão do usuario atual
     */
    @Command
    public void encerrarSessao() {
        try {
            this.sessionService.efetuarLoggout(getSessionUsuario());
            doLoggout();
            Executions.sendRedirect("/");
        } catch (ApplicationException nexp) {
            ViewHandler.showMessageErro(nexp);
        }
    }

    /**
     * Exibe tela de login
     */
    @Command
    public void renderModalLogin() {
        ViewHandler.doModal(NavigateEnum.INCLUSE_LOGIN.getUri(), null, null);
    }

    /**
     * Exibe modal de cadastro de usuario
     */
    @Command
    public void cadastroUsuario() {
        ViewHandler.doModal(NavigateEnum.INCLUSE_CADASTRO.getUri(), null, null);
    }

    /**
     * Obtem Avatar do usuario logado
     *
     * @return
     * @throws ApplicationException
     * @throws ApplicationException
     * @throws IOException
     */
    public AImage getAvatarPerfil() throws ApplicationException, ApplicationException, IOException {
        if (isSessaoValida()) {
            Login login = getCredenciaisUsuario();
            return ViewHandler.getImage(TipoEvento.AVATAR, login.getUsuario().getAvatar(), login.getUsername());
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public int getQuantidadeNaoAssistidos() {
        return quantidadeNaoAssistidos;
    }

    /**
     *
     * @param quantidadeNaoAssistidos
     */
    public void setQuantidadeNaoAssistidos(int quantidadeNaoAssistidos) {
        this.quantidadeNaoAssistidos = quantidadeNaoAssistidos;
    }
}
