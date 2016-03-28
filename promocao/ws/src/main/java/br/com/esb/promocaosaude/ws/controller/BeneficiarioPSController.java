package br.com.esb.promocaosaude.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.esb.common.core.json.JsonVO;
import br.com.esb.common.ws.controller.ControllerSimplified;
import br.com.esb.promocaosaude.core.service.BeneficiarioPSService;
import br.com.esb.promocaosaude.model.BeneficiarioPS;

/**
 * @author rsouza
 * @version 2.0 - Versao inicial - 11.03.2016
 */
@Controller
public class BeneficiarioPSController extends ControllerSimplified<BeneficiarioPS> {

	@Autowired
	private BeneficiarioPSService service;
	
	@Override
	public void executaBusca(JsonVO request, JsonVO response) throws ApplicationException {
		tratarMetodoNaoImplmentado();
	}

	@Override
	public void executaEdit(JsonVO request, JsonVO response) throws ApplicationException {
		tratarMetodoNaoImplmentado();
	}

	@Override
	public void executaSave(JsonVO request, JsonVO response) throws ApplicationException {
		tratarMetodoNaoImplmentado();
	}

	@Override
	public void executaDelete(JsonVO request, JsonVO response) throws ApplicationException {
		tratarMetodoNaoImplmentado();
	}

	@Override
	public void executaAlterarStatus(JsonVO request, JsonVO response) throws ApplicationException {
		tratarMetodoNaoImplmentado();
	}

}
