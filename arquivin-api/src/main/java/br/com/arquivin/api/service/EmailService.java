package br.com.arquivin.api.service;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import br.com.arquivin.api.common.ValidationException;
import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.api.util.TokenUtil;
import br.com.arquivin.dto.ValidationMsgTypeDTO;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.Project;
import br.com.arquivin.model.User;
import br.com.arquivin.model.enuns.RegistrationStatus;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private Configuration cfg;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ArquivinContext arquivinContext;
	
	@Value("${arquivin.service.email.alo.contato}")
    private String from;
	
	@Value("${arquivin.service.email.url.project.reset.pwd}")
	private String urlResetPwd;
	
	@Value("${arquivin.service.email.url.project.new.user}")
	private String urlNewUser;
	
	@Value("${arquivin.service.email.url.home}")
	private String urlHome;
	
	@Value("${arquivin.service.email.template.user.invite}")
	private String templateInviteUser;
	
	@Value("${arquivin.service.email.template.user.reset.pwd}")
	private String templateResetPwd;
	
	private static final String INVITE_PROJECT_SUBJECT = "Convite para colaboração de projeto";
	
	private static final String RESET_PWD_SUBJECT = "Solicitação de alteração de senha";
	
	public static final String ENCONDING = "UTF-8";
	
	
	public void sendLostPasswordEmail(String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "E-mail não encontrado!");
		}
		
		Map<String, Object> params = new HashMap<>();
		String url = getUrlResetPwd(email);
		params.put("url", url);
		params.put("userName", user.getName());
		setCompanyConfigParams(params);
		String emailBody = processEmail(params, templateResetPwd);
		Boolean success = sendEmailArquivin(email, RESET_PWD_SUBJECT, from, emailBody);
		if(!success) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha ao enviar o e-mail de alteração de senha! Tente novamente!");
		}
	}
	
	private void setCompanyConfigParams(Map<String, Object> params) {
		Company company = arquivinContext.getCompanyFromSubDomainUrl();
		params.put("headerColor", company.getHeaderColor().getBackGround());
		params.put("btnColor", company.getMainColor().getBackGround());
		params.put("logo", company.getUrlLogo());
	}

	/**
	 * Realiza o convite de um usuario externo para o projeto.
	 * @param user
	 * @param name
	 * @param email
	 * @param project
	 * @param isNewUser
	 */
	public void sendInviteUserToProject(User user, String name, String email, Project project) {
		Map<String, Object> params = createInviteParams(user, project, email, name);
		setCompanyConfigParams(params);
		String emailBody = processEmail(params, templateInviteUser);
		Boolean success = sendEmailArquivin(email, INVITE_PROJECT_SUBJECT, from, emailBody);
		if(!success) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha ao enviar o convite! Tente novamente!");
		}
	}

	/**
	 * 
	 * @param user
	 * @param project
	 * @param email
	 * @param name
	 * @param isNewUser
	 * @return
	 */
	private Map<String, Object> createInviteParams(User user, Project project, String email, String name) {
		Map<String, Object> params = new HashMap<>();
		Boolean isNewUser = user.getRegistrationStatus().equals(RegistrationStatus.INVITED);
		String url = getUrlInviteProject(email, isNewUser);
		params.put("url", url);
		params.put("project", project);
		params.put("userSentInvite", user);
		params.put("invitedName", name);
		return params;
	}

	/**
	 * Gera o url de invite para usuario novos ou já cadastrados.
	 * @param email
	 * @param isNewUser
	 * @return
	 */
	private String getUrlInviteProject(String email, boolean isNewUser) {
		String token = tokenUtil.buildToken(email);
		StringBuilder url = new StringBuilder();
		if(isNewUser) {
			url.append(setSubDomainOnUrl(urlNewUser));
			url.append("?token=").append(token);
		}else {
			url.append(setSubDomainOnUrl(urlHome));
		}
		return url.toString();
	}
	
	/**
	 * Seta o subdominio na url.
	 * @param url
	 * @return
	 */
	private String setSubDomainOnUrl(String url) {
		Company company = arquivinContext.getCompanyFromSubDomainUrl();
		return  String.format(url, company.getSubDomain());
	}

	/**
	 * Gera o url de reset de password
	 * @param email
	 * @param isNewUser
	 * @return
	 */
	private String getUrlResetPwd(String email) {
		String strToEncrypt = email+"|"+Calendar.getInstance().getTimeInMillis();
		String token = tokenUtil.buildToken(strToEncrypt);
		StringBuilder url = new StringBuilder();
		url.append(setSubDomainOnUrl(urlResetPwd));
		url.append("?token=").append(token);
		return url.toString();
	}

	/**
	 * Realiza o processamento do e-mail usando FreeMarker
	 * @param params
	 * @param templateName
	 * @return
	 */
	private String processEmail(Map<String, Object> params, String templateName) {
		try (StringWriter sw = new StringWriter();){
			Template template = cfg.getTemplate(templateName);
	        template.process(params, sw);
	        return sw.toString();
		}catch (Exception e) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha ao processar e-mail para envio. Tente novamente mais tarde");
		}
	}
	
	/**
	 * Envia e-mail
	 * @param sendTo
	 * @param subject
	 * @param from
	 * @param emailBody
	 * @return
	 */
	private boolean sendEmailArquivin(String sendTo, String subject, String from, String emailBody) {
		try{
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, ENCONDING);
					message.setTo(sendTo);
					message.setSubject(subject);
					message.setFrom(from);
					message.setText(emailBody, true);
				}
			};
			javaMailSender.send(preparator);
		}catch (Exception e) {
			LOGGER.error("Erro envio de email", e);
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	public ArquivinContext getArquivinContext() {
		return arquivinContext;
	}

	public void setArquivinContext(ArquivinContext arquivinContext) {
		this.arquivinContext = arquivinContext;
	}
}
