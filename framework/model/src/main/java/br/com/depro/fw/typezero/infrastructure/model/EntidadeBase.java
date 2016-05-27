package br.com.depro.fw.typezero.infrastructure.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 08.09.2012
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EntidadeBase implements Serializable {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = 8899845534344572276L;
    protected Long id;
    private boolean selecionado = false;

    /**
     * @return the id
     */
    public abstract Long getId();
    
    @Transient
    @JsonIgnore
    public String getIdString() {
    	if (id != null) {
    		return id.toString();
    	}
    	return null;
    }

	/**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

	@Override
    public String toString() {
		return this.getClass().getSimpleName() + "[" + id + "]";
    }

	@Transient
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public void merge(EntidadeBase entidade) {
		merge(entidade, false);
	}
	
	public EntidadeBase merge(EntidadeBase entidade, boolean isAllFields) {
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!field.toGenericString().contains("serialVersionUID") && !field.getType().isArray() && !field.getType().isPrimitive()) {
					Object valueRequest = field.get(this);
					Object valueAtual = field.get(entidade);
					
					if (valueRequest == null && valueAtual != null && (Collection.class.isAssignableFrom(field.getType()) || isAllFields)) {
						field.set(this, valueAtual);
					}
				}
			}
		} catch (IllegalArgumentException iaexp) {
		} catch (IllegalAccessException iaexp) {
		}
		return this;
	}
	
}