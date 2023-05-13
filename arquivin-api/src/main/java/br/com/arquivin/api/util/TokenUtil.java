package br.com.arquivin.api.util;

import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenUtil {

	@Autowired
	private PooledPBEStringEncryptor encryptor;

	/**
	 * Gera um token com base no parametro passado
	 * 
	 * @param param
	 * @return
	 */
	public String buildToken(final String param) {
		String token = encryptor.encrypt(param+"|"+Calendar.getInstance().getTimeInMillis());
		token = Base64.encodeBase64URLSafeString(token.getBytes());
		return token;
	}

	/**
	 * Recupera o valor que originou o token.
	 * 
	 * @param token
	 * @return
	 */
	public String unbuildToken(final String token) {
		String tk = new String(Base64.decodeBase64(token));
		return encryptor.decrypt(tk);
	}
}
