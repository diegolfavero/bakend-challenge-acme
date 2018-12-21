package br.com.acme.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.order.model.OrderItemAcme;

public interface OrderItemAcmeRepository extends JpaRepository<OrderItemAcme, Integer> {

	public Optional<OrderItemAcme> findById(Integer id);
	
	public List<OrderItemAcme> findAll();

}
