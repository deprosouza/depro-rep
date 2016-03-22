package br.com.depro.mugetsu.web.utils;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Div;


/**
 * Classe que auxilia na navegacao entre paginas<br>
 * <br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 22/04/2012</li>
 * </ul>
 *
 * @since 1.0
 */
public class NavigateHandler {

    private static String NAVIGATE_COMEBACK = "navComeback";
    private static NavigateEnum lastNavigateEnum = NavigateEnum.HOME;
    private static final Stack<NavigateEnum> stackNavigateEnum = new Stack<NavigateEnum>();

    /**
     * @param navigateEnum
     * @param param
     */
    public static void navigate(NavigateEnum destino) {
        navigate(destino, null, true);
    }
    
    /**
     * @param navigateEnum
     * @param param
     */
    public static void navigate(NavigateEnum destino, boolean isStackNavigate) {
        navigate(destino, null, isStackNavigate);
    }
    
    /**
     * @param navigateEnum
     * @param params
     */
    public static void navigate(NavigateEnum destino, Map<String, Object> params) {
    	navigate(destino, params, true);
    }

    /**
     * @param destino
     * @param params
     */
    public static void navigate(NavigateEnum destino, Map<String, Object> params, boolean isStackNavigate) {
        addParamsRequisicao(params);
        Div div = obterDivCentral();
        if (isStackNavigate) {
	        if (!lastNavigateEnum.equals(destino)) {
	            stackNavigateEnum.push(lastNavigateEnum);
	        }
	        lastNavigateEnum = destino;
        }
        String url = destino.getUri();
        Executions.createComponents(url, div, null);
    }

    /**
     * Navegar para chamar pagina anterior
     *
     * @param path
     */
    public static void navegarComeback(NavigateEnum path, NavigateEnum pathComeback) {
        navegarComebackWithParam(path, pathComeback, null);
    }

    /**
     * Navegar para chamar pagina anterior
     *
     * @param path
     * @param param
     */
    public static void navegarComebackWithParam(NavigateEnum path, NavigateEnum pathComeback, Map<String, Object> params) {
        addParamsRequisicao(params);
        ViewHandler.putInSession(NAVIGATE_COMEBACK, pathComeback.getUri());
        Div div = obterDivCentral();
        Executions.createComponents(path.getUri(), div, null);
    }

    /**
     * Adicona navegacao a pilha de enum
     *
     * @param nomeEnum
     */
    public static void push(String nomeEnum) {
        lastNavigateEnum = NavigateEnum.getEnum(nomeEnum);
        stackNavigateEnum.clear();
        stackNavigateEnum.push(NavigateEnum.HOME);
    }

    /**
     * Volta para navegacao anterior
     */
    public static void voltar() {
        voltar(null);
    }

    /**
     * Volta para navegacao anterior
     */
    public static void voltar(Map<String, Object> params) {
        String path;
        addParamsRequisicao(params);
        lastNavigateEnum = stackNavigateEnum.pop();
        path = lastNavigateEnum.getUri();
        Div div = obterDivCentral();
        Executions.createComponents(path, div, null);
    }

    /**
     * Obt�m div central que sera atualizada
     *
     * @return Div centra
     */
    private static Div obterDivCentral() {
        Div div = (Div) Path.getComponent("/winPrincipal").getFellow("conteudoPrincipal");
        removeAll(div);
        return div;
    }

    /**
     * Remove todos os filhos do componente especificado
     *
     * @param component
     */
    private static void removeAll(Component component) {
        List<Component> listComponents = component.getChildren();
        component.getChildren().removeAll(listComponents);
    }

    /**
     * Adiciona parametros na sessao da requisi��o atual
     *
     * @param params
     */
    private static void addParamsRequisicao(Map<String, Object> params) {
        if (params != null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                ViewHandler.putInSession(param.getKey(), param.getValue());
            }
        }
    }
}