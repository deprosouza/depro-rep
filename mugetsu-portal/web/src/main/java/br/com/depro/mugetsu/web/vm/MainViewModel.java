package br.com.depro.mugetsu.web.vm;


import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.web.zk.tree.ZkossDropDownMenuFactory;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15/03/2012
 */
public class MainViewModel extends BaseViewModel {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = -2626007939911424276L;
    @Wire
    private Div winPrincipal;
    @Wire
    private Image backgroundRandon;

    /**
     * Inicializa controller
     *
     * @param view
     */
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
    }

    @Override
    protected void doWithoutSession() throws ApplicationException {
    }

    /**
     * obtem plano de fundo randomicamente
     *
     * @return
     */
    @Command
    public void obterBackgroundRandon() throws IOException {
        File file = new File(getPropConfig().get("dir.path.background.images"));
        if (file.exists()) {
            int quantidadeImagens = file.listFiles().length;
            if (quantidadeImagens > 0) {
                Random random = new Random();
                backgroundRandon.setContent(new AImage(file.listFiles()[random.nextInt(quantidadeImagens)]));
            }
        }
    }

    /**
     * Renderiza menu principal
     *
     * @param component
     * @throws ApplicationException
     */
    public static void renderMenuPrincipal(Component component) throws ApplicationException {
        ZkossDropDownMenuFactory.addDropDownMenu(component);
    }

    /**
     *
     * @return
     */
    public Div getWinPrincipal() {
        return winPrincipal;
    }
}
