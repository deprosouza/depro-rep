package br.com.depro.ffsniffer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 09.03.2015
 */
@Entity
@Table(name = "CLASSE_PLAYER")
public class ClassePlayer extends EntidadeBase {

	/** Numero serial da classe */
	private static final long serialVersionUID = 5576046146555914682L;
	private Classe classe;
	private Integer level;
	private Integer itemLevel;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the classe
	 */
	@ManyToOne
	@JoinColumn(name = "classe_fk")
	public Classe getClasse() {
		return classe;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return the itemLevel
	 */
	public Integer getItemLevel() {
		return itemLevel;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param itemLevel the itemLevel to set
	 */
	public void setItemLevel(Integer itemLevel) {
		this.itemLevel = itemLevel;
	}
	
}
