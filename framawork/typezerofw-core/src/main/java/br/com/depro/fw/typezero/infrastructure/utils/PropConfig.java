package br.com.depro.fw.typezero.infrastructure.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 21.07.2012
 */
public class PropConfig implements InitializingBean {

    private String path;
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
        Assert.notNull(path, "O caminho do arquivo de propriedades deve ser especificado.");
    }

    /**
     * Retorna valor da chave desejada
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        return get(key, new Object[]{});
    }
    
    public String get(String key, Object... params) {
		String value = properties.getProperty(key);
		int seq = 0;
		for (Object param : params) {
			if (param != null) {
				value = value.replaceAll("{" + seq + "}", param.toString());
			}
			seq++;
		}
		return value;
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
        return valor;
    }

    /**
     * @param path the path to set
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public void setPath(String path) throws FileNotFoundException, IOException {
        this.path = path;
        this.properties.load(new FileInputStream(path));
    }
    
    public void reloadConfiguration() {
    	try {
			setPath(path);
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
