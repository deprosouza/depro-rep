package br.com.depro.fw.typezero.infrastructure.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author rsouza
 * @version 1.0 - versao inciao - 20.10.2015
 */
public class HashUtil {

	public static String getHash(Object object, String packageBase) {
		try {
			return getHash(getConteudo(object, packageBase));
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}
	
	public static String getHash(String string) {
		String hash = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] hashValue = messageDigest.digest(string.getBytes("UTF-8"));
			hash = new String(Hex.encodeHex(hashValue));
            
		} catch (IllegalArgumentException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return hash;
	}
	
	public static String getConteudo(Object object, String packageBase) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		if (object != null) {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!field.toGenericString().contains("serialVersionUID")) {
					if (field.getType().getPackage().getName().contains(packageBase)) {
						sb.append(getConteudo(field.get(object), packageBase));
					} else {
						if (field.get(object) != null) { 
							sb.append(field.get(object));
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	public static void visualizarConteudo(Object object, String... packagesBase) throws IllegalArgumentException, IllegalAccessException {
		if (object != null) {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!field.toGenericString().contains("serialVersionUID")) {
					if (field.getType().getPackage() != null && contains(field.getType().getPackage().getName(), packagesBase)) {
						visualizarConteudo(field.get(object), packagesBase);
					} else {
						if (field.get(object) != null) { 
							System.out.println(field.getName() + " - " + field.get(object));
						}
					}
				}
			}
		}
	}
	
	public static boolean contains(String packageName, String... packagesBase) {
		for (String pack : packagesBase) {
			if (packageName.contains(pack)) {
				return true;
			}
		}
		return false;
	}

}
