
package br.com.arquivin.api.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arquivin.model.File;
import br.com.arquivin.persistence.repo.FileRepository;

@Service
public class FileService extends GenericArquivinService<File, FileRepository> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);
	
//	@Autowired
//	private ProjectService projectService;

//	public Object[] downloadFile(Long idProject, Long idFile) {
//		LOGGER.info("BEGIN");
//		
//		File file = getRepository().loadByIdAndProject(idProject, idFile);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		BufferedOutputStream bos = new BufferedOutputStream(baos);  
//		try {
////			Object[] resp = new Object[] {file, new BufferedInputStream(new ByteArrayInputStream(CloudStorage.getFile(StorageBucketName.DEMO, file.getStorageName())))};
////			return resp;
//		} catch (Exception e) {
//			LOGGER.info("ERRO UPLOAD ARQUIVO", e);
//		} finally {
//			IOUtils.closeQuietly(baos);
//			IOUtils.closeQuietly(bos);
//		}
//		LOGGER.info("END");
//		return null;
//	}

	@Transactional
	public File deleteFile(Long idProject, Long idFile) {
		LOGGER.info("BEGIN");
		
		File file = getRepository().loadByIdAndProject(idProject, idFile);
		file.setRemoved(Boolean.TRUE);
		file.setRemovedAt(new Date());
		
//		try {
//			try {
////				CloudStorage.deleteFile(StorageBucketName.DEMO, file.getStorageName());
//			} catch (Exception e) {
//				LOGGER.info("ERRO DELETE ARQUIVO NO GOOGLE", e);
//			}
//			file.setRemoved(Boolean.TRUE);
//			
//			Project pj = file.getProject();
//			pj.getFiles().remove(file);
//			projectService.save(pj);
//
//			getRepository().delete(file);
//		} catch (Exception e) {
//			LOGGER.info("ERRO DELETE ARQUIVO", e);
//		}
		
		LOGGER.info("END");
		return file;
	}
	

}
