package br.com.arquivin.api.rest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.api.service.FileService;
import br.com.arquivin.dto.FileDTO;
import br.com.arquivin.model.File;

@RestController
@RequestMapping(value = "file")
public class FileRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileRest.class);

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ModelMapper modelMapper;	

//	@RequestMapping(value = "/{idProject}/{idFile}", method = RequestMethod.GET, produces = "application/octet-stream")
//	public void downloadFile(HttpServletResponse response, Authentication authentication, @PathVariable("idProject") Long idProject, @PathVariable("idFile") Long idFile) {
//		LOGGER.info("BEGIN");
//		try {
//			Object[] fileObj = fileService.downloadFile(idProject, idFile);
//			File f = (File) fileObj[0];
//			BufferedInputStream buff = (BufferedInputStream) fileObj[1];
//			
//			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + f.getName());
//			response.setHeader("X-ARQUIVIN-FILE", f.getName());
//			response.setContentLength(buff.available());
//			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
//			IOUtils.copyLarge(buff , bos);
//			IOUtils.closeQuietly(bos);
//			response.flushBuffer();
//		} catch (Exception e) {
//			LOGGER.info(String.format("Erro download do arquivo projeto[%s] - arquivo[%s]", idProject, idFile), e);
//		} finally {
//			LOGGER.info("END");
//		}
//	}

	@RequestMapping(value = "/{idProject}/{idFile}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<FileDTO> deleteFile(Authentication authentication, @PathVariable("idProject") Long idProject, @PathVariable("idFile") Long idFile) {
		LOGGER.info("BEGIN");
		try {
			File fileObj = fileService.deleteFile(idProject, idFile);
			FileDTO dto = modelMapper.map(fileObj, FileDTO.class);
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			LOGGER.info(String.format("Erro download do arquivo projeto[%s] - arquivo[%s]", idProject, idFile), e);
		} finally {
			LOGGER.info("END");
		}
		return ResponseEntity.notFound().build();
	}

}
