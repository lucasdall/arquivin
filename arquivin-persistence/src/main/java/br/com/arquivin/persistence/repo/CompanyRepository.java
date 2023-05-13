package br.com.arquivin.persistence.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.Company;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

	public Company findBySubDomain(String subDomain);
}
