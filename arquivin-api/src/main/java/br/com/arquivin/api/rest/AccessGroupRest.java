package br.com.arquivin.api.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.api.service.AccessGroupService;
import br.com.arquivin.dto.accessgroup.AccessGroupDTO;
import br.com.arquivin.dto.accessgroup.list.AccessGroupListViewDTO;
import br.com.arquivin.model.AccessGroup;

@RestController
@RequestMapping(value = "accessGroup")
public class AccessGroupRest {
	
	@Autowired
	private AccessGroupService accessGroupService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Lista os grupo de acessos.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<AccessGroupListViewDTO>> findAll() {
		List<AccessGroup> accessGroupList = accessGroupService.findAllByLoggedCompany();
		List<AccessGroupListViewDTO> dtoList = modelMapper.map(accessGroupList, new TypeToken<List<AccessGroupListViewDTO>>() {}.getType()); 
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	/**
	 * Lista os grupo de acessos.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="/{accessGroupId}",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AccessGroupDTO> find(@PathVariable("accessGroupId") Long accessGroupId) {
		AccessGroup accessGroup = accessGroupService.findById(accessGroupId).get();
		AccessGroupDTO dto = modelMapper.map(accessGroup, AccessGroupDTO.class); 
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Salva um novo grupo de acesso
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<AccessGroupDTO> save(@RequestBody AccessGroupDTO accessGroupDTO) {
		AccessGroup accessGroup = modelMapper.map(accessGroupDTO, AccessGroup.class);
		accessGroup = accessGroupService.saveAccessGroup(accessGroup);
		accessGroupDTO = modelMapper.map(accessGroup, AccessGroupDTO.class);
		return new ResponseEntity<>(accessGroupDTO, HttpStatus.OK);
	}

}
