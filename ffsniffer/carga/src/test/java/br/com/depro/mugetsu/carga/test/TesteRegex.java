package br.com.depro.mugetsu.carga.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesteRegex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String idioma = "<DIV CLASS=\"tab\">Seikai- | ç \\/// 7474@@no Monshō 神祕的世界 (tete (tetete))</DIV>";
		Matcher matcher = Pattern.compile("(^<DIV.*>(([\\p{L}\\d!,:\"'$%#\\.*\\-\\+@\\?\\\\/;=\\| ]+)(\\((.*)\\))*)</DIV>$)").matcher(idioma);
		if (matcher.find()) {
			System.out.println(matcher.group(3));
			System.out.println(matcher.group(5));
		}
	}
}
