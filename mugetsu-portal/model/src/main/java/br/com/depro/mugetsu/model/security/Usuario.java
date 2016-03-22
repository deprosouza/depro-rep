package br.com.depro.mugetsu.model.security;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

/**
 * Classe representa o usuario da aplicacao <br><br>
 *
 * <strong>Historico
 * <ul>
 * <li><strong>@author rsouza
 * @version 1.0 - Data: 10/03/2012</li>
 * </ul>
 *
 * @since 1.0
 *
 */
@Entity
@XmlRootElement(name = "usuario")
public class Usuario extends EntidadeBase {

    private String primeiroNome;
    private String ultimoNome;
    private String avatar;
    private Date dataNascimento;
    private Character sexo;

    /**
     * @return the id
     */
    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @return the primeiroNome
     */
    @XmlElement
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    /**
     * @param primeiroNome the primeiroNome to set
     */
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    /**
     * @return the ultimoNome
     */
    @XmlElement
    public String getUltimoNome() {
        return ultimoNome;
    }

    /**
     * @param ultimoNome the ultimoNome to set
     */
    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    /**
     * @return the dataNascimento
     */
    @XmlElement
    @Temporal(value = TemporalType.DATE)
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the sexo
     */
    @XmlElement
    public Character getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
    
    /**
     *
     * @return
     */
    @XmlElement
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
