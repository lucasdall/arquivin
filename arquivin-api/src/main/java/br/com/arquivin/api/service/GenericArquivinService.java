package br.com.arquivin.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public class GenericArquivinService<S, T extends PagingAndSortingRepository<S, Long>> {

	@Autowired
	private T repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	public Optional<S> findById(Long id) {
		return getRepository().findById(id);
	}
	
	@Transactional
	public Iterable<S> saveAll(Iterable<S> entities) {
		return getRepository().saveAll(entities);
	}

	public List<S> findAll(Sort sort) {
		if (sort == null) {
			return StreamSupport.stream(getRepository().findAll().spliterator(), false).collect(Collectors.toList());
		} else {
			return StreamSupport.stream(getRepository().findAll(sort).spliterator(), false).collect(Collectors.toList());	
		}
	}
	public List<S> findAll() {
		return findAll(null);
	}
	
	@Transactional
	public S save(S entity) {
		return getRepository().save(entity);
	}
	
	@Transactional
	public void delete(S entity) {
		getRepository().delete(entity);
	}

	@Transactional
	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}
	
	public Long count() {
		return getRepository().count();
	}

	public T getRepository() {
		return repository;
	}

	public void setRepository(T repository) {
		this.repository = repository;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
