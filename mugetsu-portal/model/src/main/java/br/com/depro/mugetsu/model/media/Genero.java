package br.com.depro.mugetsu.model.media;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26/03/2012
 */
@Entity
@XmlRootElement(name = "genero")
public class Genero extends EntidadeBase implements Comparable<Genero> {

    /** Numero de serie da classe */
	private static final long serialVersionUID = -314966844684978139L;
	private String prefixo;
    private String descricaoCurta;
    private String descricaoLonga;

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
    public Long getId() {
        return id;
    }

    /**
     * @return the prefixo
     */
    @XmlElement
    @Column(unique = true)
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
     * @return the descricaoCurta
     */
    @XmlElement
    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    /**
     * @param descricaoCurta the descricaoCurta to set
     */
    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    /**
     * @return the descricaoLonga
     */
    @XmlElement
    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    /**
     * @param descricaoLonga the descricaoLonga to set
     */
    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Genero o) {
        return this.prefixo.compareTo(o.getPrefixo());
    }
}
