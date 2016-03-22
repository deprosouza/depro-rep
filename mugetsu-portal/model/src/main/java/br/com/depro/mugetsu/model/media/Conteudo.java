package br.com.depro.mugetsu.model.media;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17.07.2012
 */
@Entity
@XmlRootElement(name = "conteudo")
public class Conteudo extends EntidadeBase {

    private List<ConteudoTitulo> titulos = new ArrayList<ConteudoTitulo>();
    private Integer numeroEpisodio;
    private boolean filler;
    private String nomeImagem;
    private String descricao;
    private Media media;
    private Date dataInsercao;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @return the titulos
     */
    @OneToMany
    @JoinColumn(name = "conteudo_id")
    @XmlElementWrapper(name = "conteudostitulos")
    @XmlElement(name = "conteudotitulo")
    public List<ConteudoTitulo> getTitulos() {
        return titulos;
    }

    /**
     * @return the numeroEpisodio
     */
    @XmlElement
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    /**
     * @return the filler
     */
    @XmlElement
    public boolean isFiller() {
        return filler;
    }

    /**
     * @return the nomeImagem
     */
    @XmlElement
    public String getNomeImagem() {
        return nomeImagem;
    }

    /**
     * @return the descricao
     */
    @XmlElement
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the media
     */
    @ManyToOne
    @JoinColumn
    @XmlTransient
    public Media getMedia() {
        return media;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(List<ConteudoTitulo> titulos) {
        this.titulos = titulos;
    }

    /**
     * @param numeroEpisodio the numeroEpisodio to set
     */
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    /**
     * @param filler the filler to set
     */
    public void setFiller(boolean filler) {
        this.filler = filler;
    }

    /**
     * @param nomeImagem the nomeImagem to set
     */
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * @return the dataInsercao
     */
    public Date getDataInsercao() {
        return dataInsercao;
    }

    /**
     * @param dataInsercao the dataInsercao to set
     */
    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}
