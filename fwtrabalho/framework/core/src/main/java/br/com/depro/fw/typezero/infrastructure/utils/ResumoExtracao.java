package br.com.depro.fw.typezero.infrastructure.utils;

import org.apache.commons.lang.StringUtils;

public class ResumoExtracao {

    private Integer quantidadeImportado = new Integer(0);
    private Integer quantidadeExistente = new Integer(0);
    private Integer quantidadeErro = new Integer(0);

    /**
     * Exibe resumo da extracao
     */
    public void exibirResumo() {
        System.out.println(StringUtils.leftPad("", 80, "#"));
        System.out.println("RESUMO DA EXTRACAO");
        System.out.println(StringUtils.leftPad("", 80, "#"));

        System.out.println("Importados com sucesso : " + this.quantidadeImportado);
        System.out.println("Arquivos já existentes : " + this.quantidadeExistente);
        System.out.println("Arquivos inexistentes  : " + this.quantidadeErro);
    }

    /**
     * @return the quantidadeImportado
     */
    public Integer getQuantidadeImportado() {
        return quantidadeImportado;
    }

    /**
     * @param quantidadeImportado the quantidadeImportado to set
     */
    public void addQuantidadeImportado() {
        this.quantidadeImportado++;
    }

    /**
     * @return the quantidadeExistente
     */
    public Integer getQuantidadeExistente() {
        return quantidadeExistente;
    }

    /**
     * @param quantidadeExistente the quantidadeExistente to set
     */
    public void addQuantidadeExistente() {
        this.quantidadeExistente++;
    }

    /**
     * @return the quantidadeErro
     */
    public Integer getQuantidadeErro() {
        return quantidadeErro;
    }

    /**
     * @param quantidadeErro the quantidadeErro to set
     */
    public void addQuantidadeErro() {
        this.quantidadeErro++;
    }
}