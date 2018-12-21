package br.com.acme.refund.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.refund.model.OrderAcme;
import br.com.acme.refund.model.PaymentAcme;
import br.com.acme.refund.util.AcmeStatusUtil;

@Service
public class OrderAcmeService {

	@Autowired
	private OrderAcmeService orderAcmeRepository;

	public void save(OrderAcme orderAcme) {
		this.orderAcmeRepository.save(orderAcme);
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

	public boolean verificaPaymentStatusFaturada(PaymentAcme paymentVerificacao, Optional<OrderAcme> orderAcme) {
		Boolean verificacaoOrderPayment = false;
		if (orderAcme.get().getStatus().equalsIgnoreCase(AcmeStatusUtil.AGUARDANDO)) {
			verificacaoOrderPayment = true;
			// Order Status
			orderAcme.get().setStatus(AcmeStatusUtil.FATURADA);
			this.save(orderAcme.get());
		}
		return verificacaoOrderPayment;
	}
	
}
