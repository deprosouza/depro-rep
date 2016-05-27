package br.com.depro.fw.typezero.infrastructure.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypezeroPagedList<T> extends ArrayList<T> implements List<T> {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 4853300489919096831L;
	private int count = 0;
	
	
	
	public TypezeroPagedList() {
		super();
	}

	public TypezeroPagedList(Collection<? extends T> c) {
		super(c);
		count = c.size();
	}

	public TypezeroPagedList(int initialCapacity) {
		super(initialCapacity);
		count = initialCapacity;
	}
	
	public TypezeroPagedList(int initialCapacity, Collection<T> c) {
		super(c);
		count = initialCapacity;
	}

	@Override
	public int size() {
		return count;
	}

}
