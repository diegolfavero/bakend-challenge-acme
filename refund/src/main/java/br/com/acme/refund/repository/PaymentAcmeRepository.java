package br.com.acme.refund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.refund.model.PaymentAcme;

public interface PaymentAcmeRepository extends JpaRepository<PaymentAcme, Integer> {
	
	public List<PaymentAcme> findAll();
	
}
