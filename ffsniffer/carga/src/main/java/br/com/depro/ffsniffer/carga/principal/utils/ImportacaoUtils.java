package br.com.depro.ffsniffer.carga.principal.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 11/02/2013
 */
public class ImportacaoUtils {

	/**
	 * Remove Tag htmls do texto
	 * 
	 * @param htmlString
	 * @return
	 */
	public static String removerHtml(String htmlString) {
      String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");
      noHTMLString = noHTMLString.replaceAll("\n", " ");
      return noHTMLString;
	}
	
	/**
	 * Transaforma Todas as Primeiras letras da palavra para maiusculo
	 * 
	 * @param s
	 * @return
	 */
	public static String toProperCase(String s) {
		String[] parts = s.split(" ");
		String camelCaseString = "";
		for (String part : parts) {
			if (StringUtils.isNotBlank(part)) {
				camelCaseString = camelCaseString
						+ part.substring(0, 1).toUpperCase()
						+ part.substring(1).toLowerCase() + " ";
			}
		}
		return camelCaseString.trim();

	}
}
