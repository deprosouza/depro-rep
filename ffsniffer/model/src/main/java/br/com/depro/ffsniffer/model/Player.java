package br.com.depro.ffsniffer.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 */
@Entity
@Table(name = "PLAYER")
public class Player extends EntidadeBase {

    private static final long serialVersionUID = -8083114973294361430L;
    private String nome;
    private ServerEnum server;
    private String idLodestone;
    private Set<Linkshell> linkshells;
    private List<ClassePlayer> classePlayers;

    /** Construtor padrao da classe */
    public Player() {
        super();
    }

    /**
     * @param server
     * @param idLodestone
     */
    public Player(ServerEnum server, String idLodestone, String nome) {
        super();
        this.server = server;
        this.idLodestone = idLodestone;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the server
     */
    @Enumerated(EnumType.STRING)
    public ServerEnum getServer() {
        return server;
    }

    /**
     * @return the idLodeStone
     */
    public String getIdLodestone() {
        return idLodestone;
    }

    /**
     * @return the linkshells
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "membros", targetEntity = Linkshell.class)
    public Set<Linkshell> getLinkshells() {
        return linkshells;
    }

    /**
     * @return the classePlayers
     */
    @OneToMany
    @JoinColumn
    public List<ClassePlayer> getClassePlayers() {
        return classePlayers;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param server
     *            the server to set
     */
    public void setServer(ServerEnum server) {
        this.server = server;
    }

    /**
     * @param idLodestone
     *            the idLodeStone to set
     */
    public void setIdLodestone(String idLodestone) {
        this.idLodestone = idLodestone;
    }

    /**
     * @param linkshells
     *            the linkshells to set
     */
    public void setLinkshells(Set<Linkshell> linkshells) {
        this.linkshells = linkshells;
    }

    /**
     * @param classePlayers
     *            the classePlayers to set
     */
    public void setClassePlayers(List<ClassePlayer> classePlayers) {
        this.classePlayers = classePlayers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idLodestone == null) ? 0 : idLodestone.hashCode());
        result = prime * result + ((server == null) ? 0 : server.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        if (idLodestone == null) {
            if (other.idLodestone != null) {
                return false;
            }
        } else if (!idLodestone.equals(other.idLodestone)) {
            return false;
        }
        if (server != other.server) {
            return false;
        }
        return true;
    }
}
