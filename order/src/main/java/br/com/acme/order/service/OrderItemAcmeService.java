package br.com.acme.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.order.model.OrderItemAcme;
import br.com.acme.order.repository.OrderItemAcmeRepository;

@Service
public class OrderItemAcmeService {
	
	@Autowired
	private OrderItemAcmeRepository orderItemAcmeRepository;

	public OrderItemAcme save(OrderItemAcme orderItemAcme) {
		return this.orderItemAcmeRepository.save(orderItemAcme);
	}
	
	public Optional<OrderItemAcme> findById(Integer id) {
		return this.orderItemAcmeRepository.findById(id);
	}
	
	public List<OrderItemAcme> findAll() {
		return this.orderItemAcmeRepository.findAll();
	}

}
