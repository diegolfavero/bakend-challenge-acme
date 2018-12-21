package br.com.acme.payment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.payment.model.OrderAcme;

@Service
public class OrderAcmeService {

	@Autowired
	private OrderAcmeService orderAcmeRepository;

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
