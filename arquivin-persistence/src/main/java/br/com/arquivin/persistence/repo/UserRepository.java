package br.com.arquivin.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public  User findByEmail(String email);
	
	@Query(value = "select * from users u where u.id_user in (select fk_user from user_company where fk_company = :idCompany)", nativeQuery=true)
	public List<User> findAllByCompany(@Param("idCompany") Long idCompany);
	
}
