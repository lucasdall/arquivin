package br.com.arquivin.api.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.api.service.ProjectService;
import br.com.arquivin.dto.FileDTO;
import br.com.arquivin.dto.ProjectDTO;
import br.com.arquivin.dto.UserDTO;
import br.com.arquivin.dto.project.list.ProjectListViewDTO;
import br.com.arquivin.model.Project;

@RestController
@RequestMapping(value = "project")
public class ProjectRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRest.class);
	
	@Autowired
	private ArquivinContext context;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Lista todas os projetos
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list/loggedCompany", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public ResponseEntity<List<ProjectListViewDTO>> findAllByLoggedCompany() {
		List<Project> projects = projectService.findAllByLoggerCompany();
		List<ProjectListViewDTO> dto = modelMapper.map(projects, new TypeToken<List<ProjectListViewDTO>>() {}.getType());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/**
	 * Lista todas os projetos do usuario
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public ResponseEntity<List<ProjectListViewDTO>> findAll() {
		LOGGER.info("BEGIN");
		try {
			List<ProjectListViewDTO> dto = projectService.findAllByUser(context.getLoggedUser(), context.getLoggedCompany());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
	}

	@RequestMapping(value="/{idProject}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProjectDTO> getById(@PathVariable("idProject") Long idProject) {
		LOGGER.info("BEGIN");
		try {
			Project model = projectService.findById(idProject).get();
			Project parent = model.getParentProject();
			if (parent != null) {
				model = parent;
			}
			ProjectDTO dto = modelMapper.map(model, ProjectDTO.class);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
	}

	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO dto) {
		LOGGER.info("BEGIN");
		try {
			Project model = modelMapper.map(dto, Project.class);
			model = projectService.saveNewProject(context.getLoggedCompany(), model);
			ProjectDTO dtoResp = modelMapper.map(model, ProjectDTO.class);
			return new ResponseEntity<>(dtoResp, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
	}
	
	@RequestMapping(value="/{idProject}/saveFileData",method = RequestMethod.POST, produces = "application/json")
	@Transactional
	public ResponseEntity<ProjectDTO> saveFileData(Authentication authentication, @PathVariable("idProject") Long idProject, @RequestBody FileDTO file) {
		LOGGER.info("BEGIN");
		try {
			Project model = projectService.saveFileData(idProject, file.getName(), file.getStorageName(), file.getSize(), context.getLoggedUser());
			Project parent = model.getParentProject();
			if (parent != null) {
				model = parent;
			}
			ProjectDTO dto = modelMapper.map(model, ProjectDTO.class);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
		
	}	

	@RequestMapping(value="/{idProject}/invite",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProjectDTO> invite(Authentication authentication, @PathVariable("idProject") Long idProject, @RequestBody UserDTO user) {
		LOGGER.info("BEGIN");
		try {
			Project model = projectService.invite(idProject, user);
			ProjectDTO dto = modelMapper.map(model, ProjectDTO.class);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
	}
	
	@RequestMapping(value="/{idProject}/remove/{idUser}",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProjectDTO> removeUserFromProject(@PathVariable("idProject") Long idProject, @PathVariable("idUser") Long idUser) {
		Project model = projectService.removeUserFromProject(idProject, idUser);
		ProjectDTO dto = modelMapper.map(model, ProjectDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/**
	 * Upload imagem do projeto.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{idProject}/uploadImg",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProjectDTO> uploadProjectImage(Authentication authentication, @PathVariable("idProject") Long idProject, @RequestParam("file") MultipartFile file) {
		LOGGER.info("BEGIN");
		try {
			Project model = projectService.findById(idProject).get();
			model = projectService.saveProjectImage(model, file);
			ProjectDTO dto = modelMapper.map(model, ProjectDTO.class);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} finally {
			LOGGER.info("END");
		}
	}	
	
}
