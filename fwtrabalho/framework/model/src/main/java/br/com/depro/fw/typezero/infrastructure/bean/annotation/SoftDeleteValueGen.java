package br.com.depro.fw.typezero.infrastructure.bean.annotation;

import java.util.HashMap;
import java.util.Map;

public abstract class SoftDeleteValueGen<SOFT_T> {
	public abstract SOFT_T getSoftDeleteValue(Boolean paramBoolean);

	public static enum SoftDeletedFieldCondition {
		EQUALS, LESSTHEN, GRATTERTHEN;
		private SoftDeletedFieldCondition() {
		}
	}

	private static Map<Class<? extends SoftDeleteValueGen>, SoftDeleteValueGen> cache = new HashMap();

	public static synchronized SoftDeleteValueGen getInstance(Class<? extends SoftDeleteValueGen> clazz) {
		SoftDeleteValueGen svg = (SoftDeleteValueGen) cache.get(clazz);
		if (svg == null) {
			try {
				cache.put(clazz, clazz.newInstance());
			} catch (Exception ex) {
			}
		}
		return (SoftDeleteValueGen) cache.get(clazz);
	}

	public abstract boolean isSoftDeleted(Object paramObject);

	public abstract SoftDeletedFieldCondition getSoftDeleteFieldCondition();
 }