package br.com.depro.fw.typezero.infrastructure.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 21.07.2012
 */
public class PropConfig implements InitializingBean {

    private String[] paths;
    private Properties properties = new Properties();

    /**
     * Construtor padrao da classe.
     */
    public PropConfig() {
        super();
    }

    /**
     * Carrega arquivo de propriedades
     * @param path
     * @throws IOException Caso ocorra algum erro de IO.
     * @throws FileNotFoundException Caso arquivo nao exista.
     */
    public PropConfig(String path) throws FileNotFoundException, IOException {
        this.setPath(path);
    }

    /**
     * @see InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(paths, "O caminho do arquivo de propriedades deve ser especificado.");
    }

    public String get(String key) {
        return get(key, new String[]{});
    }
    
    public boolean getBoolean(String key) {
    	String value = get(key);
    	if (value != null) {
    		return Boolean.valueOf(value);
    	}
    	return false;
    }
    
    /**
     * Obtem propriedade com valores dinamicos.
     * 
     * @param key
     * @param parms
     * @return 
     */
    public String get(String key, String... params) {
        int count = params.length;
        String valor = properties.getProperty(key);
        for (int i = 0; i < count; i++) {
            valor = valor.replace("{" + i + "}", params[i]);
        }
        
        String regex = ".*\\$\\{(.*)\\}.*";
        Matcher matcher = Pattern.compile(regex).matcher(valor);
        while (matcher.matches()) {
        	String group = matcher.group(1);
        	valor = valor.replaceAll("\\$\\{" + group + "\\}", get(group, params));
        	matcher = Pattern.compile(regex).matcher(valor);
        }
        return valor;
    }

    /**
     * @param path the path to set
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public void setPath(String... paths) throws FileNotFoundException, IOException {
        this.paths = paths;
        for (String path : paths) {
        	Properties prop = new Properties();
        	prop.load(new FileInputStream(path));
        	this.properties.putAll(prop);
        }
    }
    
    public void reloadConfiguration() {
    	try {
			setPath(paths);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
    }

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
}
