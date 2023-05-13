package br.com.arquivin.api.cfg;

import java.io.IOException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.arquivin.api.cfg.mail.MailCfg;

@Configuration
@EnableWebMvc
@Import(value = { CustomSecurityCfg.class, MailCfg.class })
@ComponentScan(basePackages = { "br.com.arquivin"})
@PropertySources(value = { @PropertySource("classpath:/api/endpoints_arquivin_${arquivin.env}.properties") })
public class ArquivinRestCfg {

	@Bean
	public RestTemplate setupRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return response.getStatusCode().isError();
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				System.out.println(response.getStatusCode());
			}
		});
		return restTemplate;
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mm = new ModelMapper();
		return mm;
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(@Autowired ObjectMapper mapper) {
		return new MappingJackson2HttpMessageConverter(mapper);
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	}
	
	@Bean
	public PooledPBEStringEncryptor pooledPBEStringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setAlgorithm("PBEWithSHA256And256BitAES-CBC-BC");
		encryptor.setPassword("W3bD3v1");
		encryptor.setPoolSize(20);
		return encryptor;
	}
	
}
