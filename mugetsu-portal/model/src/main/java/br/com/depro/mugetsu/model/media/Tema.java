package br.com.depro.mugetsu.model.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

/**
 * Classe representa o tema da midia <br><br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 26/03/2012</li>
 * </ul>
 *
 * @since 1.0
 *
 */
@Entity
@XmlRootElement(name = "tema")
public class Tema extends EntidadeBase implements Comparable<Tema> {

    private String prefixo;
    private String descricao;

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
     * @return the descricao
     */
    @XmlElement
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Tema o) {
        return this.prefixo.compareTo(o.getPrefixo());
    }
}
