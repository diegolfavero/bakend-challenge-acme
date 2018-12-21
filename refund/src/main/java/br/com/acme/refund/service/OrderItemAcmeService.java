package br.com.acme.refund.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.refund.model.OrderItemAcme;
import br.com.acme.refund.repository.OrderItemAcmeRepository;

@Service
public class OrderItemAcmeService {

	@Autowired
	private OrderItemAcmeRepository orderItemAcmeRepository;

	public Optional<OrderItemAcme> findById(Integer id) {
		return this.orderItemAcmeRepository.findById(id);
	}
	
	public List<OrderItemAcme> findAll() {
		return this.orderItemAcmeRepository.findAll();
	}

}
