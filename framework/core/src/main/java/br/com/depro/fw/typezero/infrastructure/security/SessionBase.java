package br.com.depro.fw.typezero.infrastructure.security;

/**
 * @author rsouza
 * @version 1.0 - versao inciao - 27.10.2015
 */
public abstract class SessionBase<T extends Object> {

	public final static String USUARIO_DESCONHECIDO = "USUARIO_DESCONHECIDO"; 
	
	public abstract String getUsername();
	
	public abstract Long getUserId();
	
	public abstract void setUserOnSession(T t);
	
	public abstract T getUserFromSession();
	
	public abstract boolean isSessionValid();
	
	public abstract void invalidateSession();
	
}
