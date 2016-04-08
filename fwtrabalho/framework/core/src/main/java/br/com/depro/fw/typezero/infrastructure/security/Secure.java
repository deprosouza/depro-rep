package br.com.depro.fw.typezero.infrastructure.security;

import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.springframework.util.SerializationUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.fw.typezero.infrastructure.utils.FWCode;

/**
 * 
 * @author rsouza
 * @version 1.0 - Versao Inicial - 30.06.2012
 */
public class Secure {

	private static final String SALT = "T7p3z3r07r4m3w0r";
	private static final String SALT_VECTOR = "Typezeroframewor";
	public static final String SALT_LIFERAY = "ORIGEM_LIFERAY";

	private static Secure singleton;

	/**
	 * Retorna a instancia da classe de criptografia
	 * 
	 * @return Instance atual
	 */
	public static Secure getInstance() {
		if (singleton == null) {
			singleton = new Secure();
		}
		return singleton;
	}

	public String encrypt(String string) {
		try {
			IvParameterSpec iv = new IvParameterSpec(SALT_VECTOR.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(SALT.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(string.getBytes());
			return Base64.encodeBase64String(encrypted).replaceAll("/", "__");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public String decrypt(String string) {
		try {
			IvParameterSpec iv = new IvParameterSpec(SALT_VECTOR.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(SALT.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(string.replaceAll("__", "/")));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public String serialize(Serializable object) throws ApplicationException {
		try {
			byte[] serialized = SerializationUtils.serialize(object);
			StandardPBEByteEncryptor byteEncryptor = new StandardPBEByteEncryptor();
			byteEncryptor.setAlgorithm("PBEWithMD5AndDES");
			byteEncryptor.setPassword(SALT);
			byte[] encrypted = byteEncryptor.encrypt(serialized);
			return Base64.encodeBase64String(encrypted);
		} catch (Exception exp) {
			throw new ApplicationException(FWCode.FW0004);
		}
	}
	
	public Object deserialize(String string) throws ApplicationException {
		if (StringUtils.isNotBlank(string)) {
			try {
				StandardPBEByteEncryptor byteEncryptor = new StandardPBEByteEncryptor();
				byteEncryptor.setAlgorithm("PBEWithMD5AndDES");
				byteEncryptor.setPassword(SALT);
				byte[] original = byteEncryptor.decrypt(Base64.decodeBase64(string));
				return SerializationUtils.deserialize(original);
			} catch (Exception exp) {
				throw new ApplicationException(FWCode.FW0006);
			}
		} else {
			throw new ApplicationException(FWCode.FW0005);
		}
	}

}