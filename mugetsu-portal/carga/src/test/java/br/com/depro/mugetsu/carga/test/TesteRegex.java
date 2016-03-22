package br.com.depro.mugetsu.carga.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TesteRegex {

	/**
	 * @param args
	 */
	@Test
	public void main() {
//		String idioma = "<div class=\"tab\">2001-05-10 to 2002-01-31</div>";
		String idioma = "<div class=\"tab\">2001-05 </div>";
		
		Pattern pattern = Pattern.compile("<(.*.)>(.*|to.*.)<(.*.)>");
        Matcher matcher = pattern.matcher(idioma);
        if (matcher.find()) {
        	pattern = Pattern.compile("(.*.)to(.*.)|(.*.)\\((.*.)\\)|(.*.)");
        	matcher = pattern.matcher(matcher.group(2));
        	if (matcher.find()) {
        		System.out.println(matcher.group(5));
        		
        	}
        } else {
        	System.out.println(false);
        }
	}
}
