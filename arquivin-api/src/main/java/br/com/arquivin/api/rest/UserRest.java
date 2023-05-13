package br.com.arquivin.api.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.api.service.UserService;
import br.com.arquivin.dto.UserDTO;
import br.com.arquivin.model.User;

@RestController
@RequestMapping(value = "user")
public class UserRest {
	
	@Autowired
	private ArquivinContext context;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Recupera o user logado.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value = "/me",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<UserDTO> loggedUser() {
		User user = context.getLoggedUser();
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/**
	 * Recupera o user logado.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value = "/{idUser}",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<UserDTO> getUser(@PathVariable("idUser") Long idUser) {
		User user = userService.findById(idUser);
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/**
	 * Recupera o user logado.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
		User newUser = modelMapper.map(userDTO, User.class);
		newUser = userService.save(newUser);
		UserDTO dto = modelMapper.map(newUser, UserDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Upload do retrato do user.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upload",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UserDTO> uploadEntityImage(@RequestParam("file") MultipartFile file) {		
		User user = userService.saveUserProfileImage(file);
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = userService.findAllByLoggedCompany();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		users.stream()
			.sorted(Comparator.comparingLong(User::getIdUser).reversed())
			.forEachOrdered((u -> dtos.add(modelMapper.map(u, UserDTO.class))));
		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}	

}
