package br.com.depro.fw.typezero.infrastructure.bean.annotation;

public class BooleanToStringValueGen extends SoftDeleteValueGen<String> {
	
	public String getSoftDeleteValue(Boolean beanValue) {
		return beanValue.booleanValue() ? "S" : beanValue == null ? "N" : "N";
	}

	public boolean isSoftDeleted(Object softDeleteValue) {
		return getSoftDeleteValue(Boolean.valueOf(true)).equals(softDeleteValue);
	}

	public SoftDeleteValueGen.SoftDeletedFieldCondition getSoftDeleteFieldCondition() {
		return SoftDeleteValueGen.SoftDeletedFieldCondition.EQUALS;
	}
 }
