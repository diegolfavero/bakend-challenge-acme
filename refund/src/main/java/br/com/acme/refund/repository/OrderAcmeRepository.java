package br.com.acme.refund.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.refund.model.OrderAcme;

public interface OrderAcmeRepository extends JpaRepository<OrderAcme, Integer> {

	public Optional<OrderAcme> findById(Integer id);
	
	public List<OrderAcme> findAll();
		
	public List<OrderAcme> findByStatus(String status);
	
}
