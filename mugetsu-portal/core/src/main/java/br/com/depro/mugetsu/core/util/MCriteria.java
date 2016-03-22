package br.com.depro.mugetsu.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import br.com.depro.fw.typezero.infrastructure.dao.DCriteria;
import br.com.depro.mugetsu.model.media.vo.FormatoAnime;
import br.com.depro.mugetsu.model.media.vo.FormatoDorama;
import br.com.depro.mugetsu.model.media.vo.FormatoMedia;

/**
 * @author rsouza
 * @version 1.0 - versao incial - 20.11.2015 
 */
public class MCriteria extends DCriteria {

	/** Numero de serie da classe */
	private static final long serialVersionUID = 2816312751200614429L;

	public FormatoMedia getFormatoMedia(String key) {
		return FormatoMedia.getEnum(getString(key));
	}
	
	public FormatoAnime getFormatoAnime(String key) {
		return FormatoAnime.getEnum(getString(key));
	}
	
	public FormatoDorama getFormatoDorama(String key) {
		return FormatoDorama.getEnum(getString(key));
	}
	
	public List<String> getListString(String key) {
		if (containsKey(key)) {
			return new ArrayList<String>((Collection<? extends String>) getObject(key));
		}
		return new ArrayList<String>();
	}
	
	public List<Long> getListLong(String key) {
		List<Long> list = new ArrayList<Long>();
		if (containsKey(key)) {
			for (String string : (Collection<? extends String>) getObject(key)) {
				list.add(Long.valueOf(string));
			}
		}
		return list;
	}
}
