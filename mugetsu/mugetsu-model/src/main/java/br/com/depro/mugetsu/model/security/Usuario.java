package br.com.depro.mugetsu.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.mugetsu.model.security.util.SexoEnum;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 26.03.2016
 */
@Entity
@Table(name = "SEC_USUARIO")
public class Usuario extends EntidadeBase {

    /** Numero serial da classe */
	private static final long serialVersionUID = -4481118621090803179L;
	private String primeiroNome;
    private String sobrenome;
    private String ultimoNome;
    private Date dataNascimento;
    private SexoEnum sexo;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SEC_USUARIO")
    @SequenceGenerator(name = "SEQ_SEC_USUARIO", sequenceName = "SEQ_SEC_USUARIO")
    public Long getId() {
        return id;
    }

    @Column(name = "PRIMEIRO_NOME")
	public String getPrimeiroNome() {
		return primeiroNome;
	}

    @Column(name = "SOBRENOME")
	public String getSobrenome() {
		return sobrenome;
	}

    @Column(name = "ULTIMO_NOME")
	public String getUltimoNome() {
		return ultimoNome;
	}

    @Column(name = "DT_NASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
	public Date getDataNascimento() {
		return dataNascimento;
	}

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
	public SexoEnum getSexo() {
		return sexo;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
    
}
