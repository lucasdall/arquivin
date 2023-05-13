package br.com.arquivin.persistence.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.Perfil;

@Repository
public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Long> {

	public Perfil findByName(String name);
	
}
