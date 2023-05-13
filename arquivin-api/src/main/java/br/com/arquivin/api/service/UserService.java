
package br.com.arquivin.api.service;

import java.io.BufferedInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arquivin.framework.utils.google.storage.CloudStorage;

import br.com.arquivin.api.common.ValidationException;
import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.api.util.TokenUtil;
import br.com.arquivin.dto.ChangePwdDTO;
import br.com.arquivin.dto.ValidationMsgTypeDTO;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.Perfil;
import br.com.arquivin.model.User;
import br.com.arquivin.model.UserCompany;
import br.com.arquivin.model.enuns.RegistrationStatus;
import br.com.arquivin.persistence.repo.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private ArquivinContext arquivinContext;
	
	@Autowired
	private UserCompanyService userCompanyService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * Recupera o User pelo email.
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	/**
	 * Recupera o User pelo id.
	 * 
	 * @param email
	 * @return
	 */
	public User findById(Long idUser) {
		User user = null;
		Optional<User> findById = userRepository.findById(idUser);
		if(findById.isPresent()) {
			user = findById.get();
			Company company = arquivinContext.getLoggedCompany();
			UserCompany userCompany = userCompanyService.find(user, company);
			user.setPerfil(userCompany.getPerfil());
		}
		return user;
	}
	
	/**
	 * Valida se o usuario existe.
	 * @param email
	 * @return
	 */
	public Boolean exists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	/**
	 * Salva um novo usuario.
	 * @param loggerUser
	 * @param user
	 * @return
	 */
	@Transactional
	public User save(User user) {
		Perfil perfil = user.getPerfil();
		user = userRepository.save(user);
		user.setPerfil(perfil);
		Company company = arquivinContext.getCompanyFromSubDomainUrl();
		userCompanyService.save(user, company);
		return user;
	}
	
	/**
	 * Atualiza o last login e status
	 * - Este metodo eh utilizado na autenticacao. 
	 * @param loggerUser
	 * @param user
	 * @return
	 */
	@Transactional
	public void updateLastLoginAndStatus(Long idUser) {
		User user = userRepository.findById(idUser).get();
		user.setLastLogin(new Date());
		user.setRegistrationStatus(RegistrationStatus.COMPLETE);
		userRepository.save(user);
	}
	
	/**
	 * Altera a senha
	 * @param user
	 * @param changePwdDTO
	 */
	@Transactional
	public void changePwd(User user, ChangePwdDTO changePwdDTO) {
		LOGGER.info("Iniciando alteracao de senha solicitado por "+user.getName()+" / id "+user.getIdUser());
		if(!user.getPwd().equals(changePwdDTO.getPwdOld())) {
			LOGGER.error("Alteracao nao realizada, senha atual digitada nao confere");
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Senha atual incorreta. Favor verificar");
		}
		LOGGER.info("Iniciando alteracao de senha");
		user.setPwd(changePwdDTO.getPwdNew());
		this.save(user);
	}
	
	/**
	 * Altera a senhas
	 * @param token
	 * @param newPwd
	 */
	@Transactional
	public void changePwdByToken(String token, String newPwd) {
		LOGGER.info("Iniciando alteracao de senha por token "+token);
		validateChangePwdToken(token);
		String email = tokenUtil.unbuildToken(token).split("\\|")[0] ;
		User user = this.findByEmail(email);
		user.setPwd(newPwd);
		this.save(user);
	}
	
	/**
	 * Valida o token
	 * @param token
	 */
	private void validateChangePwdToken(String token) {
		try {
			String params = tokenUtil.unbuildToken(token);
			Long miliseconds = Long.valueOf(params.split("\\|")[1]);  
			DateTime dateTime = new DateTime(miliseconds);
			dateTime = dateTime.plusHours(24); // TOken expira em 24 horas
			if(dateTime.isBefore(Calendar.getInstance().getTimeInMillis())) {
				throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Token expirado! Solicite a alteração de senha novamente!");
			}
		}catch (ValidationException e) {
			throw e;
		}catch (Exception e) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Token inválido! Entre em contato com o suporte");
		}
	}
	
	/**
	 * Salva imagem do profile do usuario..
	 * @param idEvent
	 * @param file
	 */
	@Transactional
	public User saveUserProfileImage(MultipartFile file) {
		User user = arquivinContext.getLoggedUser();
		if(!file.isEmpty()) {
			BufferedInputStream bis = null;
			try {
				String profilePath = String.format("%s/%s/%s","perfils", user.getIdUser(), file.getOriginalFilename());
				bis = new BufferedInputStream(file.getInputStream());
				String urlLink = CloudStorage.uploadFile("arquivin", profilePath, bis);
				user.setProfileImage(urlLink);
				user.setProfileImageContentType(file.getContentType());
				this.save(user);
			} catch (Exception e) {
				LOGGER.error("Falha ao realizar o upload da image.", e);
				throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha ao realizar o upload da imagem. Tente novamente.");
			}finally {
				IOUtils.closeQuietly(bis);
			}
		}
		return user;
	}

	/**
	 * Novo usuario com invite de token
	 * @param token
	 * @param newPwd
	 */
	@Transactional
	public void newUserByToken(String token, String newPwd) {
		LOGGER.info("Iniciando alteracao de senha por token "+token);
		validateNewUserByToken(token);
		String email = tokenUtil.unbuildToken(token).split("\\|")[0] ;
		User user = this.findByEmail(email);
		user.setPwd(newPwd);
		user.setDisabled(Boolean.FALSE);
		this.save(user);
	}
	
	/**
	 * Valida o token
	 * @param token
	 */
	private void validateNewUserByToken(String token) {
		try {
			String params = tokenUtil.unbuildToken(token);
			Long miliseconds = Long.valueOf(params.split("\\|")[1]);  
			DateTime dateTime = new DateTime(miliseconds);
			dateTime = dateTime.plusHours(24); // TOken expira em 24 horas
			if(dateTime.isBefore(Calendar.getInstance().getTimeInMillis())) {
				throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Token expirado! Solicite a alteração de senha novamente!");
			}
			
			String email = tokenUtil.unbuildToken(token).split("\\|")[0] ;
			User user = this.findByEmail(email);
			
			if(!user.getDisabled()) {
				throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Token já utilizado! Favor entrar em contato com o suporte!");
			}
		}catch (ValidationException e) {
			throw e;
		}catch (Exception e) {
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Token inválido! Entre em contato com o suporte");
		}
	}
	
	public List<User> findAllByLoggedCompany(){
		Company company = arquivinContext.getLoggedCompany();
		return userRepository.findAllByCompany(company.getIdCompany());
	}
	
}
