package br.com.arquivin.api.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.api.service.PerfilService;
import br.com.arquivin.dto.PerfilDTO;
import br.com.arquivin.model.Perfil;

@RestController
@RequestMapping(value = "perfil")
public class PerfilRest {
	
	@Autowired
	private PerfilService perfilService;

	@Autowired
	private ModelMapper modelMapper;
	

	/**
	 * Lista todas os perfis
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PerfilDTO>> findAll() {
		List<Perfil> permissions = perfilService.findAll(Sort.by(Order.desc("idPerfil")));
		List<PerfilDTO> dto = modelMapper.map(permissions, new TypeToken<List<PerfilDTO>>() {}.getType()); 
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
}
