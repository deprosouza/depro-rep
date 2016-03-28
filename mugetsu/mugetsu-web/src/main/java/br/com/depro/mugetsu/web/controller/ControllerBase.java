package br.com.depro.mugetsu.web.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.utils.PropConfig;
import br.com.depro.mugetsu.core.utils.CriteriaHelper;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 05.11.2015
 */
public class ControllerBase {

	@Autowired
	protected PropConfig propConfig;
	protected SimpleResponse response = new SimpleResponse();
	
	@SuppressWarnings("unchecked")
	protected <E extends Object> E cloneObject(E source, String... ignoreProperties) throws ApplicationException {
		try {
			E newInstance = (E) source.getClass().newInstance();
			BeanUtils.copyProperties(source, newInstance, ignoreProperties);
			return newInstance;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} 
	}
	
	protected void addImagemToView(ModelAndView modelAndView, String keyName, String path) throws IOException {
		String imageString = generateBase64(path);
		if (imageString != null) {
		    modelAndView.addObject(keyName, imageString);
		}
	}
	
	protected <E extends Object> void addPesquisaToResponse(List<E> list, CriteriaHelper mcCriteria) {
		addToResponse("list", new ArrayList<E>(list));
		if (mcCriteria.isPaged()) {
			addToResponse("size", list.size());
		}
	}
	
	protected void addToResponse(String key, Object value) {
		response.add(key, value);
	}
	
	protected void addImagemToResponse(String keyName, String path) throws IOException {
		response.add(keyName, generateBase64(path));
	}

	private String generateBase64(String path) throws IOException {
		File imagem = new File(propConfig.get("dir.path.output.img.exibicao") + path);
		if (imagem.exists()) {
			BufferedImage img = ImageIO.read(imagem);
			String imageString = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
			    ImageIO.write(img, "jpg", bos);
			    byte[] imageBytes = bos.toByteArray();
			    imageString = Base64.encodeBase64String(imageBytes);
			    bos.close();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return "data:image/jpeg;base64," + imageString;
		}
		return null;
	}
	
}
