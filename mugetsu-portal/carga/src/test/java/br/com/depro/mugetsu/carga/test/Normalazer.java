package br.com.depro.mugetsu.carga.test;

import java.text.Normalizer;

import org.junit.Test;

public class Normalazer {

	/**
	 * @param args
	 */
	@Test
	public void main() {
		System.out.println(
				Normalizer.normalize("mangabyHarutoRyō", Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
	}

}
