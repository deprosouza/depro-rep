package br.com.depro.fw.typezero.infrastructure.model;

import java.io.Serializable;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 08.09.2012
 */
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
	
}