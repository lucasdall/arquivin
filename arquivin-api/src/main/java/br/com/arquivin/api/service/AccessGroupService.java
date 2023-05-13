
package br.com.arquivin.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquivin.api.context.ArquivinContext;
import br.com.arquivin.model.AccessGroup;
import br.com.arquivin.model.Company;
import br.com.arquivin.persistence.repo.AccessGroupRepository;

@Service
public class AccessGroupService extends GenericArquivinService<AccessGroup, AccessGroupRepository> {

	@Autowired
	private ArquivinContext arquivinContext;
	
	public List<AccessGroup> findAllByLoggedCompany(){
		Company company = arquivinContext.getLoggedCompany();
		return getRepository().findAllByCompany(company);
	}
	
	@Transactional
	public AccessGroup saveAccessGroup(AccessGroup accessGroup) {
		accessGroup = getEntityManager().merge(accessGroup);
		Company company = arquivinContext.getLoggedCompany();
		accessGroup.setCompany(company);
		this.getRepository().save(accessGroup);
		return accessGroup;
	}
	
}
