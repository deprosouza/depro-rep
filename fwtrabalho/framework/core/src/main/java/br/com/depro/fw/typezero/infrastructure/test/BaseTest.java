package br.com.depro.fw.typezero.infrastructure.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.reflections.Reflections;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroBeanParse;
import br.com.depro.fw.typezero.infrastructure.spring.TypezeroSpringUtils;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Incial - 21.07.2012
 */
public abstract class BaseTest {

    protected final static String TEST = "TEST";

    /**
     * Cria context e libera sessao do hibernate
     */
    @Before
    public void constructor() {
        TypezeroBeanParse.injectDSBean(this);
        // SessionFactory sessionFactory = (SessionFactory)
        // TypezeroSpringUtils.getBean("sessionFactory");
        // Session session = SessionFactoryUtils.getSession(sessionFactory,
        // true);
        // TransactionSynchronizationManager.bindResource(sessionFactory, new
        // SessionHolder(session));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected <T extends EntidadeBase> void testCrudSimples(String pacote) {
        Reflections reflections = new Reflections(pacote);
        Set<Class<? extends TypezeroGenericService>> subTypes = reflections.getSubTypesOf(TypezeroGenericService.class);

        for (Class<?> clazz : subTypes) {
            if (clazz.isInterface()) {
                try {
                    Class<?> interfaceInstance = Class.forName(clazz.getName());

                    Set<?> serviceSubTypes = reflections.getSubTypesOf(interfaceInstance);
                    Class<?> classInstance = ((Class<?>) serviceSubTypes.iterator().next()).newInstance().getClass();

                    Class<?> entidadeClass = (Class<? extends EntidadeBase>) ((ParameterizedType) classInstance.getGenericSuperclass()).getActualTypeArguments()[0];
                    T entidadeBase = createInstance(entidadeClass);

                    TypezeroGenericService D = (TypezeroGenericService<?>) TypezeroSpringUtils.getBean(interfaceInstance);
                    T C = (T) D.salvar(entidadeBase);
                    T R = (T) D.buscarPorId(C.getId());
                    T U = (T) D.atualizar(R);
                    D.excluir(U);

                } catch (Exception exp) {
                    exp.printStackTrace();
                }

            }
        }
    }

    @SuppressWarnings({ "unchecked" })
    private <T extends EntidadeBase> T createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        EntidadeBase entidade = (EntidadeBase) clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.toGenericString().contains("serialVersionUID")) {
                if (field.getType().equals(String.class)) {
                    field.set(entidade, TEST);
                } else if (field.getType().equals(Long.class)) {
                    field.set(entidade, 1L);
                } else if (field.getType().equals(Integer.class)) {
                    field.set(entidade, Integer.SIZE);
                } else if (field.getType().equals(Boolean.class)) {
                    field.set(entidade, Boolean.TRUE);
                } else if (field.getType().equals(Date.class)) {
                    field.set(entidade, new Date());
                } else if (field.getType().equals(BigDecimal.class)) {
                    field.set(entidade, BigDecimal.ONE);
                } else if (field.getType().equals(EntidadeBase.class)) {
                    // field.set(entidade, createInstance(field.getType()));
                }

            }
        }
        return (T) entidade;
    }
}
