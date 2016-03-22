package br.com.depro.mugetsu.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.carga.dmu.service.ImportarDMUService;
import br.com.depro.mugetsu.core.service.media.GeneroService;
import br.com.depro.mugetsu.core.service.media.MediaService;
import br.com.depro.mugetsu.core.util.MCriteria;
import br.com.depro.mugetsu.model.media.Media;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;
import br.com.depro.mugetsu.web.utils.SimpleResponse;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 05.11.2015
 */
@Controller
@RequestMapping("/media")
public class MediaController extends ControllerBase {

	@Autowired
	private MediaService service;
	@Autowired
	private ImportarDMUService mugetsuService;
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView carregarBusca() {
		ModelAndView modelAndView = new ModelAndView("media/consulta");
		modelAndView.addObject("generos", generoService.buscarTodas());
		modelAndView.addObject("formatosMedias", FormatoMedia.values());
		modelAndView.addObject("formatosAnimes", FormatoAnime.values());
		modelAndView.addObject("formatosDoramas", FormatoDorama.values());
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<SimpleResponse> carregarBusca(@RequestBody MCriteria mcriteria) {
		addPesquisaToResponse(service.buscaPorAtributos(mcriteria), mcriteria);
		return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public ModelAndView viewVisualizarMedia(@PathVariable Long id) throws ApplicationException, IOException {
		ModelAndView modelAndView = new ModelAndView("media/edit");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.POST)
	public ResponseEntity<SimpleResponse> visualizarMedia(@PathVariable Long id) throws ApplicationException, IOException {
		Media media = service.buscarPorId(id);
		if (media != null) {
			Media clone = cloneObject(media, "galeriaImagens");
			addToResponse("media", clone);
			addImagemToResponse("imagem64", media.getPathImagem());
		}
		return new ResponseEntity<SimpleResponse>(response, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateorigem/{id:\\d+}", method = RequestMethod.POST)
	public ResponseEntity<Media> atualizarMediaNaOrigem(@PathVariable Long id) throws ApplicationException, IOException {
		Media clone = cloneObject(mugetsuService.atualizarDadosMedia(id), "galeriaImagens");
		addToResponse("media", clone);
		addImagemToResponse("imagem64", clone.getPathImagem());
		return new ResponseEntity<Media>(clone, HttpStatus.OK);
	}
}
