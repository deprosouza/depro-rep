package br.com.depro.fw.typezero.infrastructure.spring;

import java.lang.reflect.Field;

import br.com.depro.fw.typezero.infrastructure.annotation.TypezeroBean;

public class TypezeroBeanParse {

    public static void injectDSBean(Object objectInstance) {
        Class<?> clazz = objectInstance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            TypezeroBean dsBean = field.getAnnotation(TypezeroBean.class);
            try {
                if (dsBean != null) {
                    Class<?> bean = field.getType();
                    field.setAccessible(true);
                    field.set(objectInstance, TypezeroSpringUtils.getBean(bean));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
