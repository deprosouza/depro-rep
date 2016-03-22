package br.com.depro.mugetsu.web.utils;

/**
 * @author rsouza
 * @version 1.0 - Versao Iniciao - 10/08/2013
 */
public enum NavigateEnum {
    
    HOME                                ("/"),
    
    PAGE_DETALHE_MIDIA                  ("/WEB-INF/pages/public/media/detalhe.zul"),
    PAGE_CONSULTA_MIDIA_PUBLIC          ("/WEB-INF/pages/public/media/filtro.zul"),
    PAGE_EDITAR_MIDIA                   ("/WEB-INF/pages/public/media/editMidia.zul"),

    PAGE_USER_MIDIA                     ("/WEB-INF/pages/user/perfil/minhasMedias.zul"),
    PAGE_USER_MIDIA_PROGRAMACAO         ("/WEB-INF/pages/user/perfil/programacao.zul"),

    INCLUSE_LOGIN                       ("/WEB-INF/pages/includes/frmlogin.zul"),
    INCLUSE_HEADER                      ("/WEB-INF/pages/includes/header.zul"),
    INCLUSE_CADASTRO                    ("/WEB-INF/pages/includes/frmcadastrousuario.zul"),

    COMPISITE_LISTA_MIDIA               ("/WEB-INF/pages/composite/c_listmedia.zul"),
    COMPOSITE_LISTA_CONTEUDO            ("/WEB-INF/pages/composite/c_listconteudo.zul"),
    COMPISITE_DUAL_LIST_ROLE            ("/WEB-INF/pages/composite/c_dualList-permissao.zul"),
    ;
    
    
    private String uri;
    
    /**
     * Construtor padrao da classe
     * @param uri 
     */
    private NavigateEnum(String uri) {
        setUri(uri);
    }
    
    /**
     * Obtem enum apartir de uma string
     * @param enumn
     * @return 
     */
    public static NavigateEnum getEnum(String enumn) {
        NavigateEnum navigateEnum = HOME;
        for (NavigateEnum item : values()) {
            if (item.name().equals(enumn)) {
                navigateEnum = item;
                break;
            }
        }
        return navigateEnum;
    }

    /**
     *
     * @return
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
}
