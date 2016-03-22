package br.com.depro.mugetsu.web.vm.perfil;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;
import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.model.projeto.ProjetoConteudo;
import br.com.depro.mugetsu.negocio.service.perfil.ProjetoService;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 16.08.2013
 */
public class DetalheProgramacaoViewModel extends BaseViewModel {

    /** Numero serial da classe */
	private static final long serialVersionUID = -7482666063979270351L;
	@TypezeroBean
    private ProjetoService projetoService;
    private List<Projeto> projetosNaoAssistidos;

    @Init
    @Override
    public void initViewModel(Component view) {
        super.initViewModel(view);
    }

    @Override
    protected void doAlways() throws ApplicationException {
    }

    @Override
    protected void doWithSession() throws ApplicationException {
        setProjetosNaoAssistidos(projetoService.buscarProjetosIncompletos(Integer.MAX_VALUE, getCredenciaisUsuario(), Boolean.FALSE));
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
        Executions.sendRedirect("/");
    }

    /**
     * Fecha modal de projetos nao assistidos
     */
    @Command
    public void fecharModalProgramacao(@BindingParam("modal") Window modal) {
        setProjetosNaoAssistidos(null);
        modal.detach();
    }
    
    @Command
    public void marcarAssistido(@BindingParam("idComponente") Long idComponente,
    @BindingParam("conteudo") ProjetoConteudo conteudo, @BindingParam("accordion") Div parent) {
        try {
            conteudo.setAssistido(true);
            projetoService.atualizarProjetoConteudo(getCredenciaisUsuario(), conteudo);
            Div componente = (Div) parent.getFellow("parent" + idComponente);
            componente.detach();
            doWithSession();
        } catch (ApplicationException nexp) {
            showMessageErro(nexp);
        }
    }

    /**
     * @return the projetosNaoAssistidos
     */
    public List<Projeto> getProjetosNaoAssistidos() {
        return projetosNaoAssistidos;
    }

    /**
     * @param projetosNaoAssistidos the projetosNaoAssistidos to set
     */
    public void setProjetosNaoAssistidos(List<Projeto> projetosNaoAssistidos) {
        this.projetosNaoAssistidos = projetosNaoAssistidos;
    }
}
