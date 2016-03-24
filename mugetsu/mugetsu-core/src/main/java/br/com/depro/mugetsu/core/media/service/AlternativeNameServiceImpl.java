package br.com.depro.mugetsu.core.media.service;

import org.springframework.stereotype.Service;

import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;
import br.com.depro.mugetsu.core.media.dao.AlternativeNameDAO;
import br.com.depro.mugetsu.model.media.AlternativeName;

/**
 * @author rsouza
 * @version 1.0 - Versao Inicial - 23.03.2016
 */
@Service
public class AlternativeNameServiceImpl extends TypezeroGenericServiceImpl<AlternativeName, AlternativeNameDAO> implements AlternativeNameService {

}
