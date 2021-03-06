package br.com.depro.mugetsu.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.depro.fw.typezero.infrastructure.dao.helper.TypezeroCriteria;
import br.com.depro.mugetsu.model.media.util.FormatoAnime;
import br.com.depro.mugetsu.model.media.util.FormatoDorama;
import br.com.depro.mugetsu.model.media.util.FormatoMedia;

/**
 * @author rsouza
 * @version 1.0 -Versao Inicial - 26.03.2016
 */
public class CriteriaHelper extends TypezeroCriteria{

	/** Numero serial da classe */
	private static final long serialVersionUID = 2146859074640202086L;

	public FormatoMedia getFormatoMedia(String name) {
		if (containsKey(name)) {
			return FormatoMedia.getEnum(getString(name));
		}
		return null;
	}
	
	public FormatoAnime getFormatoAnime(String name) {
		if (containsKey(name)) {
			return FormatoAnime.getEnum(getString(name));
		}
		return null;
	}
	
	public FormatoDorama getFormatoDorama(String name) {
		if (containsKey(name)) {
			return FormatoDorama.getEnum(getString(name));
		}
		return null;
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
