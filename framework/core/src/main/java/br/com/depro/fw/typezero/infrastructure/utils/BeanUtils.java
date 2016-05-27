package br.com.depro.fw.typezero.infrastructure.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 23.12.2015
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

	public static <T extends Object> List<T> toList(String field, List list, Class<T> clazz) {
		List<T> resultList = new ArrayList<T>();
		for (Object item : list) {
			Object object = item;
			for (String slice : field.split("\\.")) {
				try {
					object = object.getClass().getMethod("get" + StringUtils.capitalize(slice)).invoke(object);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (object != null) {
				if (object instanceof Collection) {
					Matcher matcher = Pattern.compile("^[a-zA-Z]*\\.(.*.)").matcher(field);
					if (matcher.find()) {
						resultList.addAll(toList(field, list, clazz));
					} else {
						resultList.addAll((Collection<? extends T>) object);
					}
				} else {
					resultList.add((T) object);
				}
			}
		}
		return resultList;
	}
	
	public static <E extends EntidadeBase> List<E> clone(List<E> source) throws ApplicationException {
		return clone(source, new String[]{});
	}
	
	public static <E extends EntidadeBase> List<E> clone(List<E> source, String... ignoreProperties) throws ApplicationException {
		if (CollectionUtils.isNotEmpty(source)) {
			List<E> returnList = new TypezeroPagedList<E>(source.size());
			for (E e : source) {
				returnList.add(clone(e, ignoreProperties));
			}
			return returnList;
		}else {
			return new ArrayList<E>();
		}
	}
	
	public static <E extends EntidadeBase> E clone(E source) throws ApplicationException {
		return clone(source, new String[]{});
	}

	@SuppressWarnings("unchecked")
	public static <E extends EntidadeBase> E clone(E source, String... ignoreProperties) throws ApplicationException {
		try {
			E newinstante = (E) source.getClass().newInstance();
			BeanUtils.copyProperties(source, newinstante, ignoreProperties);
			return newinstante;
		} catch (InstantiationException e1) {
			throw new ApplicationException("ER0000");
		} catch (IllegalAccessException e1) {
			throw new ApplicationException("ER0000");
		}
	}
	
	
}
