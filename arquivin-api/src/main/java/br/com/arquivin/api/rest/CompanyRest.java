package br.com.arquivin.api.rest;

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
import br.com.arquivin.api.service.CompanyService;
import br.com.arquivin.dto.CompanyConfigDTO;
import br.com.arquivin.dto.CompanyDTO;
import br.com.arquivin.model.Company;

@RestController
@RequestMapping(value = "company")
public class CompanyRest {
	
	@Autowired
	private ArquivinContext context;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * Recupera o user logado.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value = "/config/{subDomain}",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CompanyConfigDTO> config(@PathVariable("subDomain") String subDomain) {
		Company c = companyService.loadCompanyBySubDomain(subDomain);
		CompanyConfigDTO dto = modelMapper.map(c, CompanyConfigDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/**
	 * Recupera o user logado.
	 * 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value = "/me",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CompanyDTO> loggedCompany() {
		Company c = context.getLoggedCompany();
		CompanyDTO dto = modelMapper.map(c, CompanyDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/logo",method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<CompanyDTO> uploadLogo(@RequestParam("file") MultipartFile file) {
		Company model = companyService.uploadLogo(file);
		CompanyDTO dto = modelMapper.map(model, CompanyDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<CompanyDTO> save(@RequestBody CompanyDTO dto) {
		Company model = modelMapper.map(dto, Company.class);
		model = companyService.save(model);
		dto = modelMapper.map(model, CompanyDTO.class);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}	

}
