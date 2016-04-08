package br.com.depro.fw.typezero.infrastructure.bean.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * Classe utilitaria para conversoes de numeros.
 * @author Marcio Amaro
 * @author Fernando Moraes
 * @version $Id: NumberUtils.java 468 2011-05-09 16:27:16Z fernando.moraes $
 */
public class NumberUtils {

    private static final BigDecimal CEM = new BigDecimal("100");

    /**
     * Metodo estatico para retornar um {@link NumberFormat}.
     * @param locale um objeto {@link Locale} que representa uma especifica 
     * regiao geografica, politica ou cultural.
     * @return {@link NumberFormat} formatado com retorno de numeros com separador (.),
     * 2 (dois) digitos e arredondamento contabil.
     */
    public static NumberFormat getNf(Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        return nf;
    }

    /**
     * Metodo estatico conversor de numero para <code>String</code>.
     * @param bd <code>BigDecimal</code> para conversao para <code>String</code>.
     * @param locale um objeto {@link Locale} que representa uma especifica 
     * regiao geografica, politica ou cultural.
     * @return valor formatado com dois digitos.
     */
    public static String numeroParaString(BigDecimal bd, Locale locale) {
        return numeroParaString(bd, locale, 2);
    }

    /**
     * Método estático conversor de {@link BigDecimal} para {@link String}.
     *
     * @param bd {@link BigDecimal} para conversão.
     * @param numeroCasasDecimais numero de casas decimais para formatacao do valor de retorno.
     * @param locale um {@link Locale} que representa uma especifica regiao geografica, politica ou cultural.
     * @return valor formatado de acordo com o numero de casas decimais passadas.
     */
    public static String numeroParaString(BigDecimal bd, Locale locale, int numeroCasasDecimais) {
        String valor = "";
        if (bd != null && bd.compareTo(BigDecimal.ZERO) != 0) {
            NumberFormat nf = getNf(locale);
            nf.setMaximumFractionDigits(numeroCasasDecimais);
            nf.setMinimumFractionDigits(numeroCasasDecimais);
            valor = nf.format(bd);
        }
        return valor;
    }

    /**
     * Método estático conversor de {@link BigDecimal} para {@link String}.
     *
     * @param bd {@link BigDecimal} para conversão.
     * @param numeroCasasDecimais numero de casas decimais para formatacao do valor de retorno.
     * @param numeroMaximoCasasInteiras numero máximo de casas inteiras para formatacao do valor de retorno.
     * @param locale um {@link Locale} que representa uma especifica regiao geografica, politica ou cultural.
     * @return valor formatado de acordo com o numero de casas decimais passadas.
     */
    public static String numeroParaString(BigDecimal bd, Locale locale, int numeroCasasDecimais, int numeroMaximoCasasInteiras) {
        String valor = "";
        if (bd != null && bd.compareTo(BigDecimal.ZERO) != 0) {
            NumberFormat nf = getNf(locale);
            nf.setMaximumFractionDigits(numeroCasasDecimais);
            nf.setMinimumFractionDigits(numeroCasasDecimais);
            nf.setMaximumIntegerDigits(numeroMaximoCasasInteiras);
            valor = nf.format(bd);
        }
        return valor;
    }

    /**
     * Configura para 2 (duas) as casas decimais de um <code>BigDecimal</code>
     * caso ele nao seja nulo.
     * @param valor <code>BigDecimal</code> para ser configurado.
     * @return o <code>BigDecimal</code> para ser configurado caso nao seja nulo.
     */
    public static BigDecimal setDecimais(BigDecimal valor) {
        if (valor != null) {
            valor = valor.setScale(2, RoundingMode.HALF_UP);
        }
        return valor;
    }

    /**
     * Retorna um <code>BigDecimal.ZERO</code> caso o valor informado seja nulo
     * ou o proprio valor se nao for.
     * @param valor valor para verificar se e nulo.
     * @return o proprio valor se nao for nulo ou <code>BigDecimal.ZERO</code> se for.
     */
    public static BigDecimal nuloParaZero(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO : valor;
    }

    /**
     * Retorna o valor de horas em decimais que esta no tipo <code>BigDecimal</code>
     * convertido em <code>String</code>.
     * @param horasDecimais o valor de horas em decimais que esta no tipo <code>BigDecimal</code>.
     * @return as horas convertidas em em <code>String</code> no padrao HH:mm.
     */
    public static String horasParaString(BigDecimal horasDecimais){
        String totalHoras = "";
        if(horasDecimais != null && horasDecimais.compareTo(BigDecimal.ZERO) > 0){
            int horasInteiras = horasDecimais.intValue();
            BigDecimal resto = horasDecimais.subtract(new BigDecimal(horasInteiras));
            resto = resto.multiply(new BigDecimal("60"));
            resto.setScale(0, RoundingMode.HALF_DOWN);
            String restoStr = resto.compareTo(BigDecimal.TEN) < 0 ? "0" + resto.intValue() : ""+resto.intValue();
            totalHoras = "" + horasInteiras + ":" + restoStr;
        }
        return totalHoras;
    }

    /**
     * Retorna o valor do tipo <code>BigDecimal</code> em porcentagem convertido para <code>String</code>.<br/>
     * Exemplo: se o valor for 0.45, retornara 45,00 %.
     * @param locale um objeto {@link Locale} que representa uma especifica 
     * regiao geografica, politica ou cultural.
     * @param valor valor passado por parametro para conversao.
     * @return <code>String</code> formatada.
     */
    public static String porcentagemParaString(BigDecimal valor, Locale locale){
        String resultado = "";
        if (valor != null && valor.compareTo(BigDecimal.ZERO) != 0) {
            valor = valor.multiply(CEM);
            resultado = getNf(locale).format(valor.doubleValue()).concat(" %");
        }
        return resultado;
    }

    /**
     * Remove do texto informado os caracteres finais que não sejam números.
     * 
     * @param text o texto que contém números.
     * @return o texto com os caracteres finais diferentes de números removidos.
     */
    public static String removeEndNotNumeric(String text) {
        String number = text;
        if (StringUtils.isNotBlank(text)) {
            number = text.trim();
            int length = number.length();
            String last = number.substring(length - 1, length);
            if (!org.apache.commons.lang.math.NumberUtils.isNumber(last)) {
                number = number.substring(0, length - 1);
                number = removeEndNotNumeric(number);
            }
        }
        return number;
    }

    /**
     * Retorna a diferenca em porcentagem entre um valor e o valor base.
     * @param valor valor que desejamos descobrir a diferenca em porcentagem.
     * @param valorBase valor base, ou seja, o valor que representa 100%.
     * @return a diferenca em porcentagem entre o valor e o valor base.
     */
    public static BigDecimal getDiferencaPorcentagem(BigDecimal valor, BigDecimal valorBase){
        BigDecimal diferencaPorcentagem = BigDecimal.ZERO;
        if (valor.compareTo(valorBase) != 0 && valorBase.compareTo(BigDecimal.ZERO) != 0) {
             diferencaPorcentagem = valor.multiply(CEM).divide(valorBase, 2, RoundingMode.HALF_UP);
             if (diferencaPorcentagem.compareTo(CEM) >= 0) {
                 diferencaPorcentagem = diferencaPorcentagem.subtract(CEM);
             } else {
                 diferencaPorcentagem = diferencaPorcentagem.negate();
             }
        }
        return diferencaPorcentagem;
    }
}
