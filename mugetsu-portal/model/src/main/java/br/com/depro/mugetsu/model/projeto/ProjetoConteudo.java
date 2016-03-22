package br.com.depro.mugetsu.model.projeto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.entidade.EntidadeBase;
import br.com.depro.mugetsu.model.media.Conteudo;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 17.07.2012
 */
@Entity
public class ProjetoConteudo extends EntidadeBase {

    private boolean assistido;
    private boolean baixado;
    private Date dataVisualizacao;
    private Date dataDownload;
    private Conteudo conteudo;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @return the assistido
     */
    public boolean isAssistido() {
        return assistido;
    }

    /**
     * @param assistido the assistido to set
     */
    public void setAssistido(boolean assistido) {
        if (assistido) {
            this.dataVisualizacao = new Date();
        } else {
            this.dataVisualizacao = null;
        }
        this.assistido = assistido;
    }

    /**
     * @return the baixado
     */
    public boolean isBaixado() {
        return baixado;
    }

    /**
     * @param baixado the baixado to set
     */
    public void setBaixado(boolean baixado) {
        if (baixado) {
            this.dataDownload = new Date();
        } else {
            this.dataDownload = null;
        }
        this.baixado = baixado;
    }

    /**
     * @return the dataVisualizacao
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDataVisualizacao() {
        return dataVisualizacao;
    }

    /**
     * @param dataVisualizacao the dataVisualizacao to set
     */
    public void setDataVisualizacao(Date dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    /**
     * @return the dataDownload
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDataDownload() {
        return dataDownload;
    }

    /**
     * @param dataDownload the dataDownload to set
     */
    public void setDataDownload(Date dataDownload) {
        this.dataDownload = dataDownload;
    }

    /**
     * @return the conteudo
     */
    @ManyToOne
    @JoinColumn(name = "conteudo_fk")
    public Conteudo getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.assistido ? 1 : 0);
        hash = 59 * hash + (this.baixado ? 1 : 0);
        hash = 59 * hash + (this.dataVisualizacao != null ? this.dataVisualizacao.hashCode() : 0);
        hash = 59 * hash + (this.dataDownload != null ? this.dataDownload.hashCode() : 0);
        hash = 59 * hash + (this.conteudo != null ? this.conteudo.hashCode() : 0);
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
        final ProjetoConteudo other = (ProjetoConteudo) obj;
        if (this.assistido != other.assistido) {
            return false;
        }
        if (this.baixado != other.baixado) {
            return false;
        }
        if (this.dataVisualizacao != other.dataVisualizacao && (this.dataVisualizacao == null || !this.dataVisualizacao.equals(other.dataVisualizacao))) {
            return false;
        }
        if (this.dataDownload != other.dataDownload && (this.dataDownload == null || !this.dataDownload.equals(other.dataDownload))) {
            return false;
        }
        if (this.conteudo != other.conteudo && (this.conteudo == null || !this.conteudo.equals(other.conteudo))) {
            return false;
        }
        return true;
    }
    
}
