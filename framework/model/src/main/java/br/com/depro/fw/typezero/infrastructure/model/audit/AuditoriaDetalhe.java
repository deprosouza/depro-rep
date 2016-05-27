package br.com.depro.fw.typezero.infrastructure.model.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;

/**
 * @author rsouza
 * @version 1.0 - versao iniciao - 26.10.2015
 */
@Entity
@Table(name = "FW_AUDIT_DETALHE")
public class AuditoriaDetalhe extends EntidadeBase {

	/** Numero de serie da classe */
	private static final long serialVersionUID = -2412260586724059302L;
	private String chave;
	private String valorDe;
	private String valorPara;
	
	@Id
	@Override
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_fw_audit_detalhe")
	@SequenceGenerator(name = "seq_fw_audit_detalhe", sequenceName = "SEQ_FW_AUDIT_DETALHE")
	public Long getId() {
		return id;
	}

	/**
	 * @return the chave
	 */
	@Column(name = "CHAVE")
	public String getChave() {
		return chave;
	}

	/**
	 * @return the valorDe
	 */
	@Column(name = "VALOR_DE", length = 1000)
	public String getValorDe() {
		return valorDe;
	}

	/**
	 * @return the valorPara
	 */
	@Column(name = "VALOR_PARA", length = 1000)
	public String getValorPara() {
		return valorPara;
	}

	/**
	 * @param chave the chave to set
	 */
	public void setChave(String chave) {
		this.chave = chave;
	}

	/**
	 * @param valorDe the valorDe to set
	 */
	public void setValorDe(String valorDe) {
		this.valorDe = valorDe;
	}

	/**
	 * @param valorPara the valorPara to set
	 */
	public void setValorPara(String valorPara) {
		this.valorPara = valorPara;
	}

}
