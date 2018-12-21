package br.com.acme.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.payment.model.PaymentAcme;
import br.com.acme.payment.repository.PaymentAcmeRepository;

@Service
public class PaymentAcmeService {

	@Autowired
	private PaymentAcmeRepository paymentAcmeRepository;

	public PaymentAcme save(PaymentAcme paymentAcme) {
		return this.paymentAcmeRepository.save(paymentAcme);
	}
	
	public List<PaymentAcme> findAll() {
		return this.paymentAcmeRepository.findAll();
	}
	
}
