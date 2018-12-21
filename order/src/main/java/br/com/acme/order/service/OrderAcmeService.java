package br.com.acme.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.order.model.OrderAcme;
import br.com.acme.order.repository.OrderAcmeRepository;

@Service
public class OrderAcmeService {

	@Autowired
	private OrderAcmeRepository orderAcmeRepository;
	
	public OrderAcme save(OrderAcme orderAcme) {
		return this.orderAcmeRepository.save(orderAcme);
	}

	public List<OrderAcme> findAll() {
		return this.orderAcmeRepository.findAll();
	}

	public Optional<OrderAcme> findById(Integer id) {
		return this.orderAcmeRepository.findById(id);
	}

	public List<OrderAcme> findByStatus(String status) {
		return this.orderAcmeRepository.findByStatus(status);
	}

}
