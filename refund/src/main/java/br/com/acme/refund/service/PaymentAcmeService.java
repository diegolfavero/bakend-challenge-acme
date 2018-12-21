package br.com.acme.refund.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.refund.model.OrderAcme;
import br.com.acme.refund.model.PaymentAcme;
import br.com.acme.refund.repository.PaymentAcmeRepository;
import br.com.acme.refund.util.AcmeStatusUtil;

@Service
public class PaymentAcmeService {

	@Autowired
	private PaymentAcmeRepository paymentAcmeRepository;

	public void save(PaymentAcme paymentAcme) {
		this.paymentAcmeRepository.save(paymentAcme);
	}
	
	public List<PaymentAcme> findAll() {
		return this.paymentAcmeRepository.findAll();
	}
	
	// RN - Order just should be refunded until ten days after confirmation and the payment is concluded.
	public long verificarDatas(Optional<OrderAcme> optionalOrderAcme) {

		long diferencaDatas = 0;

		Calendar confirmDate = Calendar.getInstance();
		confirmDate.setTime(optionalOrderAcme.get().getConfirmDate());

		LocalDateTime dataCadastro = LocalDateTime.of(
			confirmDate.get(Calendar.YEAR),
			confirmDate.get(Calendar.MONTH) + 1, 
			confirmDate.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);

		LocalDateTime dataAtual = LocalDateTime.now();

		diferencaDatas = dataCadastro.until(dataAtual, ChronoUnit.DAYS);

		return diferencaDatas;

	}

	public boolean verificaPaymentStatusAguardando(PaymentAcme paymentVerificacao, Optional<OrderAcme> orderAcme) {
		Boolean verificacaoOrderPayment = false;
		if (paymentVerificacao.getStatus().equalsIgnoreCase(AcmeStatusUtil.AGUARDANDO)) {
			verificacaoOrderPayment = true;
			// Payment Status
			paymentVerificacao.setStatus(AcmeStatusUtil.FATURADA);
			paymentVerificacao.setOrder(orderAcme.get());
			this.save(paymentVerificacao);
		}
		return verificacaoOrderPayment;
	}

}
