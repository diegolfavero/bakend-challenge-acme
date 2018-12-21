package br.com.acme.refund.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.refund.model.OrderAcme;
import br.com.acme.refund.model.PaymentAcme;
import br.com.acme.refund.service.OrderAcmeService;
import br.com.acme.refund.service.PaymentAcmeService;

@RestController
public class RefundAcmeController {

	@Autowired
	private OrderAcmeService orderAcmeService;

	@Autowired
	private PaymentAcmeService paymentAcmeService;

	// ENDPOINT: REFUND ORDER E PAYMENT
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public ResponseEntity<String> refundOrderPayment(@RequestParam("id_order") Integer idOrder) {

		Optional<OrderAcme> orderAcme = this.orderAcmeService.findById(idOrder);
		
		if (orderAcme == null) {
			return new ResponseEntity<String>("Order not found.", HttpStatus.OK);
		}

		List<PaymentAcme> ListPayment = this.paymentAcmeService.findAll();
		
		PaymentAcme paymentVerificacao = null;

		for (PaymentAcme payment : ListPayment) {
			if (payment.getOrder().getId() == orderAcme.get().getId()) {
				paymentVerificacao = payment;
				break;
			}
		}

		// RN - Order just should be refunded until ten days after confirmation and the payment is concluded.
		long diferencaDatas = this.paymentAcmeService.verificarDatas(orderAcme);

		if (diferencaDatas > 10) {
			return new ResponseEntity<String>("Order found and can be refunded.", HttpStatus.OK);
		}

		Boolean verificacaoOrderStatus = this.paymentAcmeService.verificaPaymentStatusAguardando(paymentVerificacao, orderAcme);
		Boolean verificacaoPaymentStatus = this.orderAcmeService.verificaPaymentStatusFaturada(paymentVerificacao, orderAcme);
		
		if (verificacaoOrderStatus || verificacaoPaymentStatus) {	
			return new ResponseEntity<String>("Order and Payment refunded.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Order and Payment not be refunded.", HttpStatus.OK);
		}

	}

}
