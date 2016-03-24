package br.com.depro.mugetsu.core.media.service;

import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.media.dao.GeneroDAO;
import br.com.depro.mugetsu.model.media.Genero;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 22.03.2016
 */
@Service
public class GeneroServiceImpl extends TypezeroGenericServiceImpl<Genero, GeneroDAO> implements GeneroService {

}
