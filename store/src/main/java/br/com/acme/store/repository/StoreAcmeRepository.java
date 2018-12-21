package br.com.acme.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.acme.store.model.StoreAcme;

@Transactional
public interface StoreAcmeRepository extends JpaRepository<StoreAcme, Integer> {
	
	public List<StoreAcme> findAll();
	
	public Optional<StoreAcme> findById(Integer id);
	
}