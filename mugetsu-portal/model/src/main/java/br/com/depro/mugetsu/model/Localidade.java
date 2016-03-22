package br.com.depro.mugetsu.model;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

/**
 * Classe que representa a localidade das informacoes apresentadas <br><br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 12/03/2012</li>
 * </ul>
 *
 * @since 1.0
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class Localidade extends EntidadeBase {

    /** Numero de serie da classe */
	private static final long serialVersionUID = -1243153961974685308L;
	private String prefixo;
    private char tipoLocalidade;

    /**
     * Construtor padrao da classe
     */
    public Localidade() {
        super();
    }

    /**
     * @param prefixo
     */
    public Localidade(String prefixo) {
        super();
        this.prefixo = prefixo;
    }

    /**
     * Construtor da classe
     *
     * @param id
     * @param prefixoIdiaoma
     */
    public Localidade(Long id, String prefixoIdiaoma) {
        super();
        this.setId(id);
        this.prefixo = prefixoIdiaoma;
    }
    
    private static ResourceBundle properties = ResourceBundle.getBundle("i18n", new Locale("pt", "BR"));
    
    @Transient
    public String getDescricao() {
    	return properties.getString(getPrefixo());
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    public Long getId() {
        return id;
    }

    /**
     * @return the prefixo
     */
    @XmlElement
    @Column(nullable = false, unique = true)
    public String getPrefixo() {
        return prefixo;
    }

    /**
     * @param prefixo the prefixo to set
     */
    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * @return the tipoLocalidade
     */
    @XmlElement
    public char getTipoLocalidade() {
        return tipoLocalidade;
    }

    /**
     * @param tipoLocalidade the tipoLocalidade to set
     */
    public void setTipoLocalidade(char tipoLocalidade) {
        this.tipoLocalidade = tipoLocalidade;
    }
}
