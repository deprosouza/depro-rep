package br.com.depro.fw.typezero.infrastructure.model.audit;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - versao iniciao - 26.10.2015
 */
@Entity
@Table(name = "FW_AUDIT")
public class Auditoria extends EntidadeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 1727324484777729621L;
	private Long userID;
	private Long idEntidade;
	private String nomeTabela;
	private String login;
	private Date dataEvento = new Date();
	private EventAudit evento;
	private List<AuditoriaDetalhe> detalhes;

	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FW_AUDIT")
	@SequenceGenerator(name = "SEQ_FW_AUDIT", sequenceName = "SEQ_FW_AUDIT")
	public Long getId() {
		return id;
	}

	/**
	 * @return the userID
	 */
	@Column(name = "USERID")
	public Long getUserID() {
		return userID;
	}

	/**
	 * @return the idEntidade
	 */
	@Column(name = "ENTIDADE_FK")
	public Long getIdEntidade() {
		return idEntidade;
	}

	/**
	 * @return the nomeTabela
	 */
	@Column(name = "NOME_TABELA")
	public String getNomeTabela() {
		return nomeTabela;
	}

	/**
	 * @return the login
	 */
	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	/**
	 * @return the dataEvento
	 */
	@Column(name = "DT_EVENTO")
	public Date getDataEvento() {
		return dataEvento;
	}

	/**
	 * @return the evento
	 */
	@Column(name = "EVENTO")
	@Enumerated(EnumType.STRING)
	public EventAudit getEvento() {
		return evento;
	}

	/**
	 * @return the detalhes
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AUDITORIA", nullable = false)
	public List<AuditoriaDetalhe> getDetalhes() {
		return detalhes;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	/**
	 * @param idEntidade the idEntidade to set
	 */
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	/**
	 * @param nomeTabela the nomeTabela to set
	 */
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	/**
	 * @param dataEvento the dataEvento to set
	 */
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEvento(EventAudit evento) {
		this.evento = evento;
	}

	/**
	 * @param detalhes the detalhes to set
	 */
	public void setDetalhes(List<AuditoriaDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public enum EventAudit {
		SALVAR, EDITAR, DELETAR, BUSCAR, NULL;
		
		public boolean isDeletar() {
			return this.equals(DELETAR);
		}
		
		public boolean isSalvar() {
			return this.equals(SALVAR);
		}
		
		public boolean isEditar() {
			return this.equals(EDITAR);
		}
		
		public boolean isBuscar() {
			return this.equals(BUSCAR);
		}
		
		public boolean isNull() {
			return this.equals(NULL);
		}
	}
	
}
