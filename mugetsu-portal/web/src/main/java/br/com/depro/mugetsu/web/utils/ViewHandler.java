package br.com.depro.mugetsu.web.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.xel.Function;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.scripting.bsh.BSHInterpreter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 *
 * @author rsouza
 * @version 1.0 - Versão Incial - 27/06/2012
 */
public class ViewHandler {

    /**
     * Metodo que seta na sessão parametros para auxiliar na navegação entre
     * paginas
     *
     * @param key
     * @param valor
     */
    public static void putInSession(String key, Object valor) {
        Sessions.getCurrent().setAttribute(key, valor);
    }

    /**
     * Metodo que recupera da sessão parametros para auxiliar na navegação
     * entre paginas
     *
     * @param key
     * @return
     */
    public static Object getFromSession(String key) {
        return Sessions.getCurrent().getAttribute(key);
    }

    /**
     * Metodo que recupera da sessão parametros para auxiliar na navegação
     * entre paginas
     *
     * @param key
     * @return
     */
    public static Object getFromSessionAndRemove(String key) {
        Object valor = Sessions.getCurrent().getAttribute(key);
        Sessions.getCurrent().removeAttribute(key);
        return valor;
    }
    
    /**
     * Remove parametro da sessao do usuaio
     * @param key
     */
    public static void removeFromSession(String key) {
    	Sessions.getCurrent().removeAttribute(key);
    }

    /**
     *
     * @param msg
     * @param erro
     */
    public static void showMessageErro(Throwable erro) {
        String messagem = erro.getMessage();
        String key = "\\" + messagem.substring(0, messagem.indexOf("]")) + "\\]";
        String erromsg = Labels.getLabel(key.substring(2, key.indexOf("]") - 1));
        String retorno = messagem.replaceAll(key, erromsg);
        String titulo = Labels.getLabel("label.erro.negocio");
        Messagebox.show(retorno, titulo, Messagebox.OK, Messagebox.EXCLAMATION);
    }

    /**
     * Metodo para invocar metodos localizados dentro de ZULs para eventos
     * automaticos.
     *
     * @param component
     * @param metodo
     * @param scope
     */
    public static void clickEventHandler(Component component, String metodo) {
        clickEventHandler(component, metodo, null, null);
    }

    /**
     * Metodo para invocar metodos localizados dentro de ZULs para eventos
     * automaticos.
     *
     * @param component
     * @param metodo
     * @param scope
     */
    @SuppressWarnings("rawtypes")
    public static void clickEventHandler(Component component, String metodo, Class[] types, Object[] values) {
        if (StringUtils.isNotBlank(metodo)) {
            if (component == null) {
                component = Path.getComponent("/winPrincipal/mainLayout").getFellow("conteudoPrincipal");
            }
            org.zkoss.zk.scripting.Interpreter inter = component.getPage().getInterpreter("Java");
            try {
                Function f2 = ((BSHInterpreter) inter).getFunction(component, metodo, types);
                f2.invoke(inter, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cria evento para navegacao
     *
     * @param key
     * @param path
     * @param id
     * @return
     */
    public static EventListener<Event> redirectID(final String Key, final NavigateEnum path, final Object id, final NavigateEnum pathComeback) {
        return new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                putInSession(Key, id);
                NavigateHandler.navigate(path);
            }
        };
    }
    
    /**
     * Recupera Imagem
     *
     * @param evento
     * @param nomeImagem
     * @return
     * @throws IOException
     * @throws ApplicationException
     */
    public static AImage getImage(TipoEvento evento, String nomeImagem, String... params) throws ApplicationException, IOException {
        AImage imagem = null;
        try {
            if (StringUtils.isNotBlank(nomeImagem)) {
                imagem = new AImage(UploadUtils.getFullPathOutput(nomeImagem, evento, params));
            }
        } catch (Exception exp) {
        }
        return imagem;
    }

    /**
     * Metodo basico para criar modais
     *
     * @param path
     * @param parent
     */
    public static void doModal(String path, Component parent, Map<Object, Object> params) {
        Window window = (Window) Executions.createComponents(path, parent, params);
        window.doModal();
    }
}
