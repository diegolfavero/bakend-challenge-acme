package br.com.acme.payment.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.payment.model.OrderAcme;
import br.com.acme.payment.model.PaymentAcme;
import br.com.acme.payment.service.OrderAcmeService;
import br.com.acme.payment.service.PaymentAcmeService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentAcmeService paymentService;

	@Autowired
	private OrderAcmeService orderAcmeService;

	// ENDPOINT: INSERIR PAYMENT
	@RequestMapping(value = "/payment/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertPayment(@RequestParam("status") String status,
		@RequestParam("number_card") Long numberCard, @RequestParam("date") String date,
		@RequestParam("id_order") Integer idOrder) {

		Optional<OrderAcme> optionalOrderAcme = this.orderAcmeService.findById(idOrder);
		
		if (optionalOrderAcme == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			
			try {

				PaymentAcme payment = new PaymentAcme();
				
				payment.setNumberCard(numberCard);
				payment.setStatus(status);
				payment.setOrder(optionalOrderAcme.get());
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date paymDate = dateFormat.parse(date);
				payment.setPaymDate(paymDate);
				
				this.paymentService.save(payment);

			} catch (ParseException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Error to insert Payment!", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<String>("Payment inserted successfully.", HttpStatus.OK);
		}
	}

	// ENDPOINT: PESQUISAR TODOS
	@RequestMapping(value = "/payment/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<PaymentAcme>> findAll() {

		List<PaymentAcme> listaPayment = this.paymentService.findAll();

		if (listaPayment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<PaymentAcme>>(listaPayment, HttpStatus.OK);

	}

}
