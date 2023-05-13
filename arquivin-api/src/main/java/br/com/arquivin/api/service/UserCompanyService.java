package br.com.arquivin.api.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arquivin.model.Company;
import br.com.arquivin.model.Perfil;
import br.com.arquivin.model.User;
import br.com.arquivin.model.UserCompany;
import br.com.arquivin.model.UserCompanyID;
import br.com.arquivin.persistence.repo.UserCompanyRepository;

@Service
public class UserCompanyService {
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Transactional
	public UserCompany save(User user, Company company) {
		UserCompany userCompany = null;
		Perfil perfil = user.getPerfil();
		UserCompanyID id = new UserCompanyID(user, company);
		Optional<UserCompany> op = userCompanyRepository.findById(id);
		if(!op.isPresent()) {
			userCompany = new UserCompany(id);
			userCompany.setPerfil(perfil);
		}else {
			userCompany = op.get();
			if(user.getPerfil() != null) {
				userCompany.setPerfil(perfil);
			}
		}		
		userCompanyRepository.save(userCompany);
		return userCompany;
	}
	
	/**
	 * Recupera o relacionamento entre usuario e empresa.
	 * @param user
	 * @param company
	 * @return
	 */
	public UserCompany find(User user, Company company) {
		UserCompanyID id = new UserCompanyID(user, company);
		Optional<UserCompany> opt = userCompanyRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
}
