package br.com.acme.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.payment.model.PaymentAcme;

public interface PaymentAcmeRepository extends JpaRepository<PaymentAcme, Integer> {
	
	public List<PaymentAcme> findAll();
	
}
