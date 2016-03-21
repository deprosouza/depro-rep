package br.com.depro.mugetsu.core;

import org.junit.Test;

import br.com.depro.fw.typezero.infrastructure.test.BaseTest;

public class ServicesTests extends BaseTest{

	@Test
	public void startup() {
		super.testCrudSimples("br.com.depro.mugetsu");
	}
}
