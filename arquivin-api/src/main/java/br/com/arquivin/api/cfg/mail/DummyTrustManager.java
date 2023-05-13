package br.com.arquivin.api.cfg.mail;

import java.security.cert.CertificateException;

import javax.net.ssl.X509TrustManager;

public class DummyTrustManager implements X509TrustManager {

	public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return new java.security.cert.X509Certificate[0];
	}
}