
package br.com.arquivin.api.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arquivin.framework.utils.google.storage.CloudStorage;

import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.dto.SignedUrlDTO;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.File;
import br.com.arquivin.model.Project;
import br.com.arquivin.persistence.repo.FileRepository;

@Service
public class GcsService extends GenericArquivinService<File, FileRepository> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GcsService.class);

	@Autowired
	private ArquivinContext arquivinContext;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ProjectService projectService;

	@Value("${arquivin.bucket}")
	private String arquivinBucket;

	@Value("${arquivin.storage.expiration}")
	private Integer expirationInSeconds;

	/**
	 * Cria a url para baixa do arquivo.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public SignedUrlDTO createGetUrl(Long fileId) throws Exception {
		File file = fileService.findById(fileId).get();
		Project pj = file.getProject();
		String storagePath = createStoragePath(pj);
		String storageFileName = file.getStorageName();
		String url = CloudStorage.createGetUrl(storagePath, storageFileName, expirationInSeconds);
		url = url+"&response-content-disposition="+URLEncoder.encode("attachment;filename="+file.getName()+", \"UTF-8\"", StandardCharsets.UTF_8.name());
		
		// Salva a tentativa de download
		file.increaseDownloadCount();
		fileService.save(file);
		
		return new SignedUrlDTO(url, storageFileName, file.getName());
	}
	
	/**
	 * Cria aurl para upload de arquivo.
	 * @param projectId
	 * @param fileName
	 * @param contentType
	 * @return
	 * @throws Exception
	 */
	public SignedUrlDTO createPutUrl(Long projectId, String fileName, String contentType) throws Exception {
		Project project = projectService.findById(projectId).get(); 
		String storagePath = createStoragePath(project);
		String storageFileName = generateStorageFileName(fileName, UUID.randomUUID().toString());
		StringBuilder fullFileNamePath = new StringBuilder(storagePath);
		String url = CloudStorage.createPutUrl(fullFileNamePath.toString(), storageFileName, contentType, expirationInSeconds);
		return new SignedUrlDTO(url, storageFileName, fileName);
	}

	/**
	 * Cria o nome do arquivo no storage utilizando um UUID
	 * 
	 * @param fileName
	 * @return
	 */
	private String generateStorageFileName(String fileName, String uuidFileName) {
		StringBuilder sb = new StringBuilder(UUID.randomUUID().toString());
		String extenstion = FilenameUtils.getExtension(fileName);
		sb.append(".").append(extenstion);
		return sb.toString();
	}

	/**
	 * Cria o path onde o arquivo vai estar ou esta.
	 * 
	 * @param project
	 * @return
	 */
	private String createStoragePath(Project project) {
		String folder = getFolder(project);

		Company company = arquivinContext.getLoggedCompany();
		StringBuilder sb = new StringBuilder(arquivinBucket);
		sb.append("/").append(company.getCnpj()).append(folder);
		return sb.toString();
	}

	/**
	 * Cria a pasta
	 * 
	 * @param project
	 * @return
	 */
	private String getFolder(Project project) {
		if (project == null) {
			return "";
		}
		String folder = getFolder(project.getParentProject());
		StringBuilder sb = new StringBuilder(folder);
		sb.append("/").append(project.getIdProject());
		return sb.toString();
	}

}
