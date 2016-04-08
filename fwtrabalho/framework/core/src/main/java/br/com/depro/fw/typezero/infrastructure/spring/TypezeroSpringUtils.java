package br.com.depro.fw.typezero.infrastructure.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 25.07.2012
 */
public class TypezeroSpringUtils {

    protected static ApplicationContext context;

    /**
     * Retorna instancia do contexto da aplicacao
     *
     * @return
     */
    public static ApplicationContext getInstance() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        }
        return context;
    }

    /**
     * Metodo que retorna o bean gerenciado pelo spring requisitado
     *
     * @param beanName
     * @return Bean requisitado
     * @throws ApplicationException Caso algum erro ocorra
     */
    public static Object getBean(String beanName) {
        if (context == null) {
            getInstance();
        }
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
        if (context == null) {
            getInstance();
        }
        return (E) context.getBean(clazz);
    }
}
