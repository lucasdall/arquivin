
package br.com.arquivin.api.service;

import java.io.BufferedInputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arquivin.framework.utils.google.storage.CloudStorage;

import br.com.arquivin.api.common.ValidationException;
import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.dto.UserDTO;
import br.com.arquivin.dto.ValidationMsgTypeDTO;
import br.com.arquivin.dto.project.list.ProjectListViewDTO;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.File;
import br.com.arquivin.model.Project;
import br.com.arquivin.model.User;
import br.com.arquivin.model.enuns.RegistrationStatus;
import br.com.arquivin.persistence.repo.ProjectRepository;

@Service
public class ProjectService extends GenericArquivinService<Project, ProjectRepository> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private ArquivinContext arquivinContext;
	
	@Transactional
	public Project saveFileData(Long idProject, String fileName, String fileNameStorage, Long fileSize, User uploadedBy) {
		LOGGER.info("BEGIN");
		Project pj = findById(idProject).get();

		File f = new File();
		f.setProject(pj);
		f.setCreateAt(new Date());
		f.setName(fileName);
		f.setStorageName(fileNameStorage);
		f.setUploadedBy(uploadedBy);
		f.setSize(fileSize);

		pj.getFiles().add(0,f);
		pj = save(pj);
		LOGGER.info("END");
		return pj;
	}
	
	/**
	 * Salva um novo usuario.
	 * @param loggerUser
	 * @param newUser
	 * @return
	 */
	@Transactional
	public Project saveNewProject(Company loggedCompany, Project project) {
		if (project.getIdProject() == null) {
			loggedCompany = getEntityManager().merge(loggedCompany);
			project.setCreateAt(new Date());
			project.setCompany(loggedCompany);
			project = this.save(project);
			Project parent = project.getParentProject(); 
			if (parent != null) {
				Project pr = getRepository().findById(parent.getIdProject()).get();
				pr.getChildProjects().add(0, project);
				pr.getChildProjects().forEach(child -> child.setParentProject(pr));
				return this.save(pr);
			}
			return project;
		} else {
			Project pj = findById(project.getIdProject()).get();
			pj.setUpdateAt(new Date());
			pj.setDescription(project.getDescription());
			pj.setFullDescription(project.getFullDescription());
			pj.setName(project.getName());
			pj.setDisabled(project.getDisabled());
			if (pj.getParentProject() != null && pj.getDisabled()) {
				// Se for subprojeto e estiver disabled devera fazer a exlusao logica de todos arquivos
				pj.getFiles().forEach(f -> {
					f.setRemoved(Boolean.TRUE);
					f.setRemovedAt(new Date());
				});
				return this.save(pj).getParentProject();
			}

			project = this.save(pj);
		}
		
		return project;
	}

	@Transactional
	public Project invite(Long idProject, UserDTO userDTO) {
		LOGGER.info("BEGIN");
		Project project = findById(idProject).get();
		User user = userService.findByEmail(userDTO.getEmail());
		if (user != null) {
			// usuario ja tem cadastro
			LOGGER.info("Usuario ja cadastrado. Fara apenas o vinculo ao projeto.");
			if (!project.getAllowedUsers().contains(user)) {
				project.getAllowedUsers().add(0, user);
			}
		} else {
			LOGGER.info("Usuario novo. Vai enviar o convite por email");
			user = new User();
			user.setEmail(userDTO.getEmail());
			user.setName(userDTO.getName());
			user.setPwd(RandomStringUtils.randomAlphanumeric(10));
			user.setDisabled(Boolean.TRUE);
			user.setRegistrationStatus(RegistrationStatus.INVITED);			
			user.setPerfil(perfilService.getPerfilUsuarioExterno());
			userService.save(user);
			
			project.getAllowedUsers().add(0, user);
		}
		project = save(project);
		emailService.sendInviteUserToProject(user, userDTO.getName(), userDTO.getEmail(), project);
		LOGGER.info("END");
		return project;
	}	
	
	/**
	 * Salva imagem do projeto
	 * @param idEvent
	 * @param file
	 */
	@Transactional
	public Project saveProjectImage(Project project, MultipartFile file) {
		if(!file.isEmpty()) {
			project = getEntityManager().merge(project);
			try {
				String pjPath = String.format("%s/%s/%s",project.getCompany().getCnpj(), project.getIdProject(), file.getOriginalFilename());
				BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
				String urlLink = CloudStorage.uploadFile("arquivin", pjPath, bis);
				project.setImgUrl(urlLink);
				IOUtils.closeQuietly(bis);
				getRepository().save(project);
			} catch (Exception e) {
				LOGGER.error("Falha ao realizar o upload da image.", e);
				throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha ao realizar o upload da imagem. Tente novamente.");
			}
		}
		return project;
	}

	@Transactional(readOnly = true)
	public List<ProjectListViewDTO> findAllByUser(User user, Company c) {
		List<Project> models = null;
		if ("ADMIN".equals(user.getPerfil().getName())) {
			models = getRepository().findProjectsByUserAdmin(user.getIdUser(), c.getIdCompany());
		} else {
			models = getRepository().findProjectsByUser(user.getIdUser(), c.getIdCompany());
		}
		
		List<ProjectListViewDTO> dto = modelMapper.map(models, new TypeToken<List<ProjectListViewDTO>>() {}.getType()); 
		return dto;
	}
	
	/**
	 * Lista todos os projetos da empresa
	 * @param user
	 * @return
	 */
	public List<Project> findAllByLoggerCompany() {
		return getRepository().findAllByCompany(arquivinContext.getLoggedCompany());
	}
	
	/**
	 * Remove o usuario do projeto.
	 * @param idProject
	 * @param idUser
	 * @return 
	 */
	public Project removeUserFromProject(Long idProject, Long idUser) {
		Project project = getRepository().findById(idProject).get();
		User user = userService.findById(idUser);
		project.getAllowedUsers().remove(user);
		getRepository().save(project);
		
		user.setAccessGroup(null);
		userService.save(user);
		
		return project;
	}
	
}
