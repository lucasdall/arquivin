package br.com.arquivin.api.cfg.mail;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class MailCfg {
	
	@Value("${arquivin.service.email.host}")
    private String host;

    @Value("${arquivin.service.email.port}")
    private Integer port;
    
    @Value("${arquivin.service.email.contato}")
    private String username;

    @Value("${arquivin.service.email.contato.password}")
    private String password;

	@Bean
	public freemarker.template.Configuration freeMarkerConfiguration() {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/api/template/");
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
	}
	
	@Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setProtocol("smtp");

        return javaMailSender;
    }

	/**
	 * Properties padrao de envio de email pelo google.
	 * @return
	 */
    private Properties getMailProperties() {
        Properties properties = new Properties();
		properties.setProperty("mail.smtp.ssl.enable", "false");
		properties.setProperty("mail.smtp.socketFactory.class", "br.com.arquivin.api.cfg.mail.DummySSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.debug", "true");
        return properties;
    }
	
	
}
