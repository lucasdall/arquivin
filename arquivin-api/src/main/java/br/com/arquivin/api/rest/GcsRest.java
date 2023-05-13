package br.com.arquivin.api.rest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.api.service.GcsService;
import br.com.arquivin.dto.SignedUrlDTO;

@RestController
@RequestMapping(value = "gcsRest")
public class GcsRest {

	@Autowired
	private GcsService gcsService;

	@RequestMapping(value = "/createGetUrl/{fileId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<SignedUrlDTO> creatGetUrl(@PathVariable("fileId") Long fileId) throws Exception {
		SignedUrlDTO dto = gcsService.createGetUrl(fileId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createPutUrl/{projectId}/{fileName}/{contentType}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<SignedUrlDTO> creatPutUrl(@PathVariable("projectId") Long projectId, @PathVariable("fileName") String fileName, @PathVariable("contentType") String contentType) throws Exception {
		SignedUrlDTO dto = gcsService.createPutUrl(projectId, new String(Base64.decodeBase64(fileName)), new String(Base64.decodeBase64(contentType)));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
