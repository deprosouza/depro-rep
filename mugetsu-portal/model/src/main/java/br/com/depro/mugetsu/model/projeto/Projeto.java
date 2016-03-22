package br.com.depro.mugetsu.model.projeto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.util.CollectionUtils;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.cenum.Status;
import br.com.depro.mugetsu.model.leecher.Grupo;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.security.Login;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial
 */
@Entity
public class Projeto extends EntidadeBase {

    private String label;
    private int quantidadeEpisodios;
    private Date dataInicial;
    private Date dataFinal;
    private BigDecimal ranking = BigDecimal.ZERO;
    private Media media;
    private Login usuario;
    private Status status;
    private Grupo grupo;
    private List<ProjetoConteudo> conteudosProjeto = new ArrayList<ProjetoConteudo>();
    private int quantidadeAssitidos;
    private int quantidadeNaoAssistidos;

    /**
     * Obtem quantidade de itens assistidos
     *
     * @return
     */
    @Transient
    public int getQuantidadeAssistidos() {
        if (quantidadeAssitidos == 0 && !CollectionUtils.isEmpty(conteudosProjeto)) {
            for (ProjetoConteudo projetoConteudo : conteudosProjeto) {
                if (projetoConteudo.isAssistido()) {
                    quantidadeAssitidos++;
                }
            }
        }
        return quantidadeAssitidos;
    }

    /**
     * Obtem quantidade de itens nao assistidos
     *
     * @return
     */
    @Transient
    public int getQuantidadeNaoAssistidos() {
        if (quantidadeNaoAssistidos == 0 && !CollectionUtils.isEmpty(conteudosProjeto)) {
            quantidadeNaoAssistidos = getConteudosProjeto().size() - getQuantidadeAssistidos();
        }
        return quantidadeNaoAssistidos;
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the quantidadeEpisodios
     */
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    /**
     * @param quantidadeEpisodios the quantidadeEpisodios to set
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * @return the dataInicial
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the raking
     */
    public BigDecimal getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(BigDecimal ranking) {
        this.ranking = ranking;
    }

    /**
     * @return the media
     */
    @ManyToOne
    @JoinColumn
    public Media getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * @return the usuario
     */
    @ManyToOne
    @JoinColumn
    public Login getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the grupo
     */
    @ManyToOne
    @JoinColumn
    public Grupo getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the conteudosProjeto
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projeto_fk")
    public List<ProjetoConteudo> getConteudosProjeto() {
        return conteudosProjeto;
    }

    /**
     * @param conteudosProjeto the conteudosProjeto to set
     */
    public void setConteudosProjeto(List<ProjetoConteudo> conteudosProjeto) {
        this.conteudosProjeto = conteudosProjeto;
    }

    /**
     * @return the status
     */
    @Enumerated(value = EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 97 * hash + this.quantidadeEpisodios;
        hash = 97 * hash + (this.dataInicial != null ? this.dataInicial.hashCode() : 0);
        hash = 97 * hash + (this.dataFinal != null ? this.dataFinal.hashCode() : 0);
        hash = 97 * hash + (this.ranking != null ? this.ranking.hashCode() : 0);
        hash = 97 * hash + (this.media != null ? this.media.hashCode() : 0);
        hash = 97 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        hash = 97 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 97 * hash + (this.grupo != null ? this.grupo.hashCode() : 0);
        hash = 97 * hash + (this.conteudosProjeto != null ? this.conteudosProjeto.hashCode() : 0);
        hash = 97 * hash + this.quantidadeAssitidos;
        hash = 97 * hash + this.quantidadeNaoAssistidos;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if (this.quantidadeEpisodios != other.quantidadeEpisodios) {
            return false;
        }
        if (this.dataInicial != other.dataInicial && (this.dataInicial == null || !this.dataInicial.equals(other.dataInicial))) {
            return false;
        }
        if (this.dataFinal != other.dataFinal && (this.dataFinal == null || !this.dataFinal.equals(other.dataFinal))) {
            return false;
        }
        if (this.ranking != other.ranking && (this.ranking == null || !this.ranking.equals(other.ranking))) {
            return false;
        }
        if (this.media != other.media && (this.media == null || !this.media.equals(other.media))) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.grupo != other.grupo && (this.grupo == null || !this.grupo.equals(other.grupo))) {
            return false;
        }
        if (this.conteudosProjeto != other.conteudosProjeto && (this.conteudosProjeto == null || !this.conteudosProjeto.equals(other.conteudosProjeto))) {
            return false;
        }
        if (this.quantidadeAssitidos != other.quantidadeAssitidos) {
            return false;
        }
        if (this.quantidadeNaoAssistidos != other.quantidadeNaoAssistidos) {
            return false;
        }
        return true;
    }
    
}
