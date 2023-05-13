package br.com.arquivin.converter;

import javax.persistence.AttributeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordConverter implements AttributeConverter<String, String> {
	
	@Autowired
	private PooledPBEStringEncryptor encryptor;
	
	public PasswordConverter() {
		encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setAlgorithm("PBEWithSHA256And256BitAES-CBC-BC");
		encryptor.setPassword("W3bD3v1");
		encryptor.setPoolSize(20);
	}

	@Override
	public String convertToDatabaseColumn(String arg0) {
		return encryptor.encrypt(arg0);
	}

	@Override
	public String convertToEntityAttribute(String arg0) {
		return encryptor.decrypt(arg0);
	}

}
