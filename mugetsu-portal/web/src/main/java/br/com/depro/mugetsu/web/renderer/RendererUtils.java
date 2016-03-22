package br.com.depro.mugetsu.web.renderer;

import br.com.depro.mugetsu.web.composite.pojo.ListConteudoPojo;

/**
 *
 * @author rsouza
 * @version. - Versao Inicial - 09.09.2012
 */
public class RendererUtils {

    /**
     * Retorna caminho da imagen dependendo que for passado
     *
     * @param opcao
     * @return
     */
    public static String renderImagemCheckOrNotCheck(ListConteudoPojo data, int opcao) {
        String valor = "fechar.png";
        if (data.getProjetoConteudo() != null && opcao != 2) {
            if (opcao == 0) {
                valor = data.getProjetoConteudo().isBaixado() ? "check.png" : "fechar.png";
            } else {
                valor = data.getProjetoConteudo().isAssistido() ? "check.png" : "fechar.png";
            }
        } else {
            valor = data.getConteudo().isFiller() ? "check.png" : "fechar.png";
        }
        return "/imagens/icones/" + valor;
    }
}
