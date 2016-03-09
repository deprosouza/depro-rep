package br.com.depro.ffsniffer.web.util;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Div;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11.03.2015
 */
public class NavigateHandler {

    private static String NAVIGATE_COMEBACK = "navComeback";
    private static PageURIEnum lastNavigateEnum = PageURIEnum.HOME;
    private static final Stack<PageURIEnum> stackNavigateEnum = new Stack<PageURIEnum>();

    /**
     * @param navigateEnum
     * @param param
     */
    public static void navigate(PageURIEnum destino) {
        navigate(destino, null);
    }

    /**
     * @param navigateEnum
     * @param params
     */
    public static void navigate(PageURIEnum navigateEnum, Map<String, Object> params) {
        addParamsRequisicao(params);
        Div div = obterDivCentral();
        if (!lastNavigateEnum.equals(navigateEnum)) {
            stackNavigateEnum.push(lastNavigateEnum);
        }
        lastNavigateEnum = navigateEnum;
        String url = navigateEnum.getUri();
        Executions.createComponents(url, div, null);
    }

    /**
     * Navegar para chamar pagina anterior
     *
     * @param path
     */
    public static void navegarComeback(PageURIEnum path, PageURIEnum pathComeback) {
        navegarComebackWithParam(path, pathComeback, null);
    }

    /**
     * Navegar para chamar pagina anterior
     *
     * @param path
     * @param param
     */
    public static void navegarComebackWithParam(PageURIEnum path, PageURIEnum pathComeback, Map<String, Object> params) {
        addParamsRequisicao(params);
        ViewHandler.setSession(NAVIGATE_COMEBACK, pathComeback.getUri());
        Div div = obterDivCentral();
        Executions.createComponents(path.getUri(), div, null);
    }

    /**
     * Adicona navegacao a pilha de enum
     *
     * @param nomeEnum
     */
    public static void push(String nomeEnum) {
        lastNavigateEnum = PageURIEnum.getEnum(nomeEnum);
        stackNavigateEnum.clear();
        stackNavigateEnum.push(PageURIEnum.HOME);
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
     * Obtem div central que sera atualizada
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
     * Adiciona parametros na sessao da requisicao atual
     *
     * @param params
     */
    private static void addParamsRequisicao(Map<String, Object> params) {
        if (params != null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                ViewHandler.setSession(param.getKey(), param.getValue());
            }
        }
    }
}