package br.com.arquivin.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.Company;
import br.com.arquivin.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	
	@Query(nativeQuery = true, value = "select * from project p, project_user pu "
			+ " where "
			+ " (p.id_project = pu.fk_project and pu.fk_user = :userId and p.fk_company = :companyId and parent_project is null)"
			+ " order by id_project desc")
	List<Project> findProjectsByUser(@Param("userId") Long useId, @Param("companyId") Long companyId);

	@Query(nativeQuery = true, value = "select * from project p, users u, user_company uc, company c, perfil per "
			+ " where "
			+ " uc.FK_PERFIL = per.ID_PERFIL and per.NAME = 'ADMIN' and u.id_user = :userId and u.id_user = uc.fk_user and uc.fk_company = c.id_company and c.id_company = p.fk_company and p.fk_company = :companyId and parent_project is null "			
			+ " order by id_project desc")
	List<Project> findProjectsByUserAdmin(@Param("userId") Long useId, @Param("companyId") Long companyId);

	List<Project> findAllByCompany(Company company);
}
