package br.com.depro.ffsniffer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 */
@Entity
@Table(name = "LINKSHELL")
public class Linkshell extends EntidadeBase {

	private static final long serialVersionUID = 259688733260761802L;
	private String nome;
	private String idLodestone;
	private ServerEnum server;
	private Set<Player> membros;

	/** Construtor padrao da classe */
	public Linkshell() {
		super();
	}

	/**
	 * @param idLodestone
	 * @param server
	 */
	public Linkshell(String idLodestone, ServerEnum server, String nome) {
		super();
		this.idLodestone = idLodestone;
		this.server = server;
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
	 * @return the diLodestone
	 */
	public String getIdLodestone() {
		return idLodestone;
	}

	/**
	 * @return the server
	 */
	@Enumerated(EnumType.STRING)
	public ServerEnum getServer() {
		return server;
	}

	/**
	 * @return the membros
	 */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Player.class)
	@JoinTable(name = "LSPlayer", joinColumns = @JoinColumn(name = "linkshell_fk"), inverseJoinColumns = @JoinColumn(name = "player_fk"))
	public Set<Player> getMembros() {
		return membros;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param diLodestone the diLodestone to set
	 */
	public void setIdLodestone(String idLodestone) {
		this.idLodestone = idLodestone;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(ServerEnum server) {
		this.server = server;
	}

	/**
	 * @param membros the membros to set
	 */
	public void setMembros(Set<Player> membros) {
		this.membros = membros;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idLodestone == null) ? 0 : idLodestone.hashCode());
        result = prime * result + ((server == null) ? 0 : server.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Linkshell other = (Linkshell) obj;
        if (idLodestone == null) {
            if (other.idLodestone != null)
                return false;
        } else if (!idLodestone.equals(other.idLodestone))
            return false;
        if (server != other.server)
            return false;
        return true;
    }

}
