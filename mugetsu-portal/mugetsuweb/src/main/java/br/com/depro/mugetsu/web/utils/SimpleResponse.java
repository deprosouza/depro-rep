package br.com.depro.mugetsu.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 20.11.2015
 */
public class SimpleResponse {

	private Map<String, Object> response = new HashMap<String, Object>();
	
	public void add(String key, Object value) {
		if (value != null) {
			response.put(key, value);
		}
	}

	/**
	 * @return the response
	 */
	public Map<String, Object> getResponse() {
		return response;
	}
}
