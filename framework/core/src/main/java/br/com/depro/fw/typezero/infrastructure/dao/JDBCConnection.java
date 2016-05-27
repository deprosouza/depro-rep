package br.com.depro.fw.typezero.infrastructure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.InitializingBean;

public class JDBCConnection implements InitializingBean {

    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection singleton = null;

    public Connection getConnection() {
    	singleton = null;
    	try {
			afterPropertiesSet();
		} catch (Exception e) {
		}
        return singleton;
    }

	public void afterPropertiesSet() throws Exception {
		if (singleton == null) {
            try {
                Class.forName(driver);
                singleton = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
            } catch (ClassNotFoundException e) {
                System.out.println("Classe nao encontrada, adicione o driver nas bibliotecas.");
            }
        }
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
