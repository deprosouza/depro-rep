package br.com.depro.mugetsu.negocio.service.media;

import br.com.depro.fw.typezero.infrastructure.exception.CoreException;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericService;
import br.com.depro.mugetsu.model.media.Tema;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 10.08.2012
 */
public interface TemaService extends TypezeroGenericService<Tema> {

	/**
	 * Obtem tema pelo prefixo
	 * 
	 * @param prefixo
	 * @return Tema
	 * @throws CoreException Caso algum erro ocorra.
	 */
	Tema obterTemaPorPrefixo(String prefixo) throws CoreException;
}
