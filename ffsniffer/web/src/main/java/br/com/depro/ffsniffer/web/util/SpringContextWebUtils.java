package br.com.depro.ffsniffer.web.util;


import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Sessions;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;

/**
 * Classe de infraestrutura para auxiliar na interacao com o spring<br><br>
 * @author rsouza
 * @version 1.0 - Data: 01/05/2012</li>
 * @since 1.0
 */
public class SpringContextWebUtils extends TypezeroSpringUtils {

    static {
        if (context == null) {
            ServletContext servletContext = (ServletContext) Sessions.getCurrent().getWebApp().getServletContext();
            context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        }
    }

    /**
     * Metodo que retorna o bean gerenciado pelo spring requisitado
     *
     * @param beanName
     * @return Bean requisitado
     * @throws ApplicationException Caso algum erro ocorra
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * Metodo que retorna o bean gerenciado pelo spring requisitado
     *
     * @param <E>
     *
     * @param beanName
     * @return Bean requisitado
     * @throws ApplicationException Caso algum erro ocorra
     */
    public static <E> Object getBean(Class<E> clazz) {
        return (E) context.getBean(clazz);
    }
}
