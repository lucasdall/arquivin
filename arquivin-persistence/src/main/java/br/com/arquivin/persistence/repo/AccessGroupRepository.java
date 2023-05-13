package br.com.arquivin.persistence.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.AccessGroup;
import br.com.arquivin.model.Company;

@Repository
public interface AccessGroupRepository extends PagingAndSortingRepository<AccessGroup, Long> {

	public List<AccessGroup> findAllByCompany(Company company);
	
}
