package br.com.depro.fw.typezero.infrastructure.bean.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
public class DateUtils {

	public static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	public static final String DEFAULT_HOUR_PATTERN = "HH:mm";
	private static final String KEY_ANO = "data.anos.letra";
    private static final String KEY_MES = "data.meses.letra";
    private static final String KEY_DIA = "data.dias.letra";
	
	public static Date getAsDate(String string) {
		return getAsDate(string, DEFAULT_PATTERN);
	}
	
	public static Date getAsDate(String string, String pattern) {
		if (StringUtils.isNotBlank(string)) {
			try {
				return new SimpleDateFormat(pattern).parse(string);
			} catch (ParseException pexp) {
			}
		}
		return null;
	}

	/**
	 * Retorna uma data configurada utilizando o Mes e Ano da data passada por
	 * parametro, o dia sendo inserido o primeiro dia do mes (1) e os demais
	 * parametros da data como hora, minuto, segundo e milesegundo sendo
	 * inserido o valor 0 (Zero).
	 * 
	 * @param data
	 *            data passada por parametro para alteracao.
	 * @return data formatada.
	 */
	public static Date configuraMesAno(Date data) {
		if (data == null) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DATE, 1);
		return configuraHorarioData(cal.getTime(), true);
	}

	/**
	 * Retorna a data configurada para o primeiro horario do dia.
	 * 
	 * @param data
	 *            data a ser configurada.
	 * @return a data configurada para o primeiro horario do dia.
	 */
	public static Date getDataComPrimeiroHorario(Date data) {
		if (data == null) {
			return data;
		}
		return configuraHorarioData(data, true);
	}

	/**
	 * Retorna a data configurada para o ultimo horario do dia.
	 * 
	 * @param data
	 *            data a ser configurada.
	 * @return a data configurada para o primeiro horario do dia.
	 */
	public static Date getDataComUltimoHorario(Date data) {
		if (data == null) {
			return data;
		}
		return configuraHorarioData(data, false);
	}

	/**
	 * Configura a data para o primeiro horario do dia ou para o ultimo de
	 * acordo com o parametro.
	 * 
	 * @param data
	 *            data a ser configurada.
	 * @param isPrimeiroHorario
	 *            identificador para primeiro ou ultimo horario. Se for
	 *            <code>true</code> sera configurado o primeiro horario e se for
	 *            <code>false</code> o ultimo horario.
	 * @return a data configurada para o primeiro horario do dia.
	 */
	private static Date configuraHorarioData(Date data, boolean isPrimeiroHorario) {
		if (data == null) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, isPrimeiroHorario ? 0 : 23);
		cal.set(Calendar.MINUTE, isPrimeiroHorario ? 0 : 59);
		cal.set(Calendar.SECOND, isPrimeiroHorario ? 0 : 59);
		cal.set(Calendar.MILLISECOND, isPrimeiroHorario ? 0 : 999);
		return cal.getTime();
	}

	/**
	 * Configura a data passada por parametro com o ultimo dia do mes e primeiro
	 * horario ou ultimo horario do dia conforme parametro.
	 * 
	 * @param data
	 *            data a ser configurada.
	 * @param isPrimeiroHorario
	 *            verdadeiro para configurar com o primeiro horario e falso para
	 *            o ultimo horario do dia.
	 * @return a data passada por parametro com o ultimo dia do mes e primeiro
	 *         horario ou ultimo horario do dia conforme parametro.
	 */
	public static Date getDataComUltimoDiaMes(Date data, boolean isPrimeiroHorario) {
		if (data == null) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return configuraHorario(cal, isPrimeiroHorario);
	}

	/**
	 * Configura a data passada por parametro com o ultimo dia do mes e primeiro
	 * horario ou ultimo horario do dia conforme parametro.
	 * 
	 * @param data
	 *            data a ser configurada.
	 * @param isPrimeiroHorario
	 *            verdadeiro para configurar com o primeiro horario e falso para
	 *            o ultimo horario do dia.
	 * @return a data passada por parametro com o ultimo dia do mes e primeiro
	 *         horario ou ultimo horario do dia conforme parametro.
	 */
	public static Date getDataComPrimeiroDiaMes(Date data, boolean isPrimeiroHorario) {
		if (data == null) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return configuraHorario(cal, isPrimeiroHorario);
	}

	private static Date configuraHorario(Calendar cal, boolean isPrimeiroHorario) {
		cal.set(Calendar.HOUR_OF_DAY, isPrimeiroHorario ? 0 : 23);
		cal.set(Calendar.MINUTE, isPrimeiroHorario ? 0 : 59);
		cal.set(Calendar.SECOND, isPrimeiroHorario ? 0 : 59);
		cal.set(Calendar.MILLISECOND, isPrimeiroHorario ? 0 : 999);
		return cal.getTime();
	}

	/**
	 * Calcula o numero de meses entre duas datas base (sempre iniciando em dia
	 * 1o do mês) informadas.
	 * 
	 * @param dataInicial
	 *            data inicial. Ignora dia e hora da data, considerando apenas o
	 *            dia 01 do mes.
	 * @param dataFinal
	 *            data final. Ignora dia e hora da data, considerando apenas o
	 *            dia 01 do mes.
	 * @return numero de meses entre a data inicial e o mes final. Ex:
	 *         data1=13/01/2011 11:00:00 e data2=01/03/2011 01:00:12 retorna 2
	 *         que é referente a quantidade de meses entre 01/01/2011 e
	 *         01/03/2011. Caso a data inicial for nula, retornara 0(zero) como
	 *         numero de meses.
	 */
	public static int calculaMesesEntreDatas(Date dataInicial, Date dataFinal) {
		if (dataInicial == null) {
			return 0;
		}
		if (dataInicial.after(dataFinal)) {
			return -1;
		}
		DateTime dtInicial = new DateTime(configuraMesAno(dataInicial));
		DateTime dtFinal = new DateTime(configuraMesAno(dataFinal));
		Months m = Months.monthsBetween(dtInicial, dtFinal);
		return m.getMonths();
	}

	private static Period configuraDataCalculoIdade(Date dataInicial, Date dataFinal, Locale locale) {
		Calendar d1 = Calendar.getInstance(locale);
		Calendar d2 = Calendar.getInstance(locale);
		d1.setTime(dataInicial);
		d2.setTime(dataFinal);
		DateTime inicio = new DateTime(d1.getTime());
		DateTime fim = new DateTime(d2.getTime());
		Period periodo = new Period(inicio, fim);
		return periodo;
	}

	/**
	 * Calcula a idade(anos) a partir da data passada por parametro. Para o
	 * calculo sera usada a data atual e o local padrao (default locale).
	 * 
	 * @param dataInicial
	 *            data inicial para o calculo.
	 * @return a idade.
	 */
	public static int calculaIdade(Date dataInicial) {
		return calculaIdade(dataInicial, new Date(), Locale.getDefault());
	}

	/**
	 * Calcula a idade(anos) a partir da data e do local passados por parametro.
	 * Para o calculo sera usada a data atual.
	 * 
	 * @param dataInicial
	 *            data inicial para o calculo.
	 * @param locale
	 *            local usado para a configuracao.
	 * @return a idade.
	 */
	public static int calculaIdade(Date dataInicial, Locale locale) {
		return calculaIdade(dataInicial, new Date(), locale);
	}

	/**
	 * Calcula a idade(anos) a partir da data inicial, a data final e do local
	 * passados por parametro.
	 * 
	 * @param dataInicial
	 *            data inicial para o calculo.
	 * @param dataFinal
	 *            data final para o calculo, geralmente usada a data atual.
	 * @param locale
	 *            local usado para a configuracao.
	 * @return a idade.
	 */
	public static int calculaIdade(Date dataInicial, Date dataFinal, Locale locale) {
		Period periodo = configuraDataCalculoIdade(dataInicial, dataFinal, locale);
		return periodo.getYears();
	}

	/**
	 * Calcula o periodo de dias entre duas datas.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final.
	 * @return inteiro contendo o intervalo de dias.
	 */
	public static int calculaPeriodoDias(Date dataInicial, Date dataFinal) {
		return calculaPeriodoDias(dataInicial, dataFinal, Locale.getDefault());
	}

	/**
	 * Calcula o periodo de dias entre duas datas.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final.
	 * @param locale
	 *            locale.
	 * @return inteiro contendo o intervalo de dias.
	 */
	public static int calculaPeriodoDias(Date dataInicial, Date dataFinal, Locale locale) {
		Calendar d1 = Calendar.getInstance(locale);
		Calendar d2 = Calendar.getInstance(locale);
		d1.setTime(dataInicial);
		d2.setTime(dataFinal);
		DateTime inicio = new DateTime(d1.getTime());
		DateTime fim = new DateTime(d2.getTime());
		Days days = Days.daysBetween(inicio, fim);
		return days.getDays();
	}

	public static String formatarData(Date date, String pattern) {
		return formatarData(date, pattern, null);
	}

	public static String formatarData(Date date, String pattern, Locale locale) {
		if (date != null) {
			DateFormat df = null;
			if (locale == null) {
				df = new SimpleDateFormat(pattern);
			} else {
				df = new SimpleDateFormat(pattern, locale);
			}
			return df.format(date);
		}
		return null;
	}

	/**
	 * Retorna a primeira data do ano informado.
	 * 
	 * @param ano
	 *            atributo que representa o ano.
	 * @return Retorna a primeira data do ano informado.
	 */
	public static Date getPrimeiraDataDoAno(Integer ano) {
		Date data = new Date();
		if (ano == null) {
			data = null;
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, 0);
		data = DateUtils.configuraMesAno(cal.getTime());
		data = DateUtils.getDataComPrimeiroHorario(data);
		return data;
	}

	/**
	 * Retorna a ultima data do ano informado.
	 * 
	 * @param ano
	 *            atributo que representa o ano.
	 * @return Retorna a ultima data do ano informado.
	 */
	public static Date getUltimaDataDoAno(Integer ano) {
		Date data = new Date();
		if (ano == null) {
			data = null;
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		data = DateUtils.getDataComUltimoHorario(cal.getTime());
		return data;
	}

	/**
	 * Adiciona o numero de meses a data.<br>
	 * Utiliza para o calculo o metodo
	 * {@link DateTime#plus(org.joda.time.ReadablePeriod) } da biblioteca JODA.
	 * 
	 * @param data
	 *            data para adicionar os meses.
	 * @param meses
	 *            meses para adicionar na data.
	 * @return a data com os meses adicionada.
	 */
	public static Date adicionaMeses(Date data, int meses) {
		DateTime dt = new DateTime(data);
		dt = dt.plus(Period.months(meses));
		return dt.toDate();
	}

	/**
	 * Adiciona o numero de dias a data.
	 * 
	 * @param data
	 *            data para adicionar os dias.
	 * @param dias
	 *            dias para adicionar na data.
	 * @return a data com os dias adicionados.
	 */
	public static Date adicionaDias(Date data, int dias) {
		if (data == null) {
			return data;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, dias);
		return cal.getTime();
	}
	
	public static String calculaIdadePadraoAMD(Date dataInicial) {
        return calculaIdadePadraoAMD(dataInicial, new Date(), Locale.getDefault());
    }

    public static String calculaIdadePadraoAMD(Date dataInicial, Locale locale) {
        return calculaIdadePadraoAMD(dataInicial, new Date(), locale);
    }

    public static String calculaIdadePadraoAMD(Date dataInicial, Date dataFinal) {
        return calculaIdadePadraoAMD(dataInicial, dataFinal, Locale.getDefault());
    }

    public static String calculaIdadePadraoAMD(Date dataInicial, Date dataFinal, Locale locale) {
        ResourceBundle properties = ResourceBundle.getBundle("i18n");
        Period periodo = configuraDataCalculoIdade(dataInicial, dataFinal, locale);
        int a = periodo.getYears();
        int m = periodo.getMonths();
        int w = periodo.getWeeks();
        int d = periodo.getDays();
        if(w > 0){
            d =+ (w * 7);
        }
        return "" + a + properties.getString(KEY_ANO) + " "
                + m + properties.getString(KEY_MES) + " "
                + d + properties.getString(KEY_DIA);
    }
}
