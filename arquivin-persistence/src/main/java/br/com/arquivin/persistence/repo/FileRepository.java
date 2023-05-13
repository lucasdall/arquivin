package br.com.arquivin.persistence.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.arquivin.model.File;

@Repository
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
	
	@Query("select f from File f where f.idFile = :idFile and f.project.idProject = :idProject")
	File loadByIdAndProject(@Param("idProject") Long idProject, @Param("idFile") Long idFile);

}
