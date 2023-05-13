package br.com.arquivin.persistence.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.arquivin.model.UserCompany;
import br.com.arquivin.model.UserCompanyID;

public interface UserCompanyRepository extends PagingAndSortingRepository<UserCompany, UserCompanyID>{

}
