package br.com.depro.mugetsu.web.vm.media;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.model.projeto.Projeto;
import br.com.depro.mugetsu.web.composite.ListMedia;
import br.com.depro.mugetsu.web.vm.BaseViewModel;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 16.08.2013
 */
public class ConsultaMidiaUsuarioViewModel extends BaseViewModel {

	/** Numero serial da classe */
	private static final long serialVersionUID = 2103486564503108892L;
	@Wire
    private ListMedia lmProjetos;
    private List<Projeto> projetosUsuario;

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
        this.lmProjetos.setLayout(ListMedia.Layout.PROJETOUSUARIO);
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     * @return the projetosUsuario
     */
    public List<Projeto> getProjetosUsuario() {
        return projetosUsuario;
    }

    /**
     * @param projetosUsuario the projetosUsuario to set
     */
    public void setProjetosUsuario(List<Projeto> projetosUsuario) {
        this.projetosUsuario = projetosUsuario;
    }
}
