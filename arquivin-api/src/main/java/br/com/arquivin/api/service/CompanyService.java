
package br.com.arquivin.api.service;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arquivin.framework.utils.google.storage.CloudStorage;
import com.arquivin.framework.utils.google.storage.StorageBucketName;

import br.com.arquivin.api.common.ValidationException;
import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.dto.ValidationMsgTypeDTO;
import br.com.arquivin.model.Company;
import br.com.arquivin.persistence.repo.CompanyRepository;

@Service
public class CompanyService extends GenericArquivinService<Company, CompanyRepository> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private ArquivinContext arquivinContext;
	
	@Value("${arquivin.storage.url.public}")
	private String urlStorage;

	@Transactional
	public Company uploadLogo(MultipartFile file) {
		LOGGER.info("BEGIN");
		Company company = arquivinContext.getLoggedCompany();
		String fileName = null;
		try {
			fileName = "logo."+ FilenameUtils.getExtension(file.getOriginalFilename());
			CloudStorage.uploadFile(StorageBucketName.ARQUIVIN, company.getCnpj()+"/"+fileName, file.getInputStream());
		} catch (Exception e) {
			LOGGER.info("ERRO UPLOAD ARQUIVO", e);
			throw new ValidationException(ValidationMsgTypeDTO.ERROR, "Falha no upload da logo. Tente novamente.");
		}
		company.setFileName(fileName);
		String urlLogo = createCompanyLogoPublicUrl(company.getCnpj(), fileName);
		company.setUrlLogo(urlLogo);
		this.save(company);
		LOGGER.info("END");
		return company;
	}
	
	/**
	 * Cria a url para acesso a logo do projeto.
	 * @param cnpj
	 * @param fileName
	 * @return
	 */
	private String createCompanyLogoPublicUrl(String cnpj, String fileName) {
		StringBuilder sb = new StringBuilder(urlStorage);
		sb.append("/").append(StorageBucketName.ARQUIVIN.toString()).append("/").append(cnpj).append("/").append(fileName);
		return sb.toString();
	}
	
	/**
	 * Busca a empresa pelo seu subdominio
	 * @param subDomain
	 * @return
	 */
	public Company loadCompanyBySubDomain(String subDomain) {
		return this.getRepository().findBySubDomain(subDomain);
	}
	
	@Override
	@Transactional
	public Company save(Company c) {
		if (c.getIdCompany() == null) {
			return super.save(c);
		} else {
			Company update = findById(c.getIdCompany()).get();
			update.setHeaderColor(c.getHeaderColor());
			update.setMainColor(c.getMainColor());
			return super.save(update);
		}
	}
}
