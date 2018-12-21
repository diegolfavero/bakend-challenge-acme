package br.com.acme.order.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import br.com.acme.order.model.OrderAcme;
import br.com.acme.order.model.OrderItemAcme;
import br.com.acme.order.service.OrderAcmeService;
import br.com.acme.order.service.OrderItemAcmeService;

@RestController
public class OrderAcmeController {

	@Autowired
	private OrderAcmeService orderService;

	@Autowired
	private OrderItemAcmeService orderItemAcmeService;

	// ENDPOINT: INSERIR ORDER
	@RequestMapping(value = "/order/insertOrder", method = RequestMethod.POST)
	public ResponseEntity<String> insertOrder(@RequestParam("address") String address,
		@RequestParam("date") String date, @RequestParam("status") String status,
		@RequestParam("itens") Integer[] itens) {

		List<OrderItemAcme> listaOrderItemAcme = new ArrayList<>();
		OrderAcme orderAcme = new OrderAcme();
		
		for (Integer item : itens) {
			Optional<OrderItemAcme> optionalOrderItemAcme = this.orderItemAcmeService.findById(item);
			listaOrderItemAcme.add(optionalOrderItemAcme.get());
		}
		
		if (listaOrderItemAcme.size() == 0) {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			
			try {
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date dataFormatada = dateFormat.parse(date);
				
				orderAcme.setAddress(address);
				orderAcme.setConfirmDate(dataFormatada);
				orderAcme.setStatus(status);
				orderAcme.getOrderItemAcme().addAll(listaOrderItemAcme);
				
				this.orderService.save(orderAcme);
				
			} catch (ParseException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Error to insert Order.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		}
		
		return new ResponseEntity<String>("Order inserted successfully.", HttpStatus.OK);
	}

	// ENDPOINT: PESQUISAR TODOS
	@RequestMapping(value = "/order/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<OrderAcme>> findAll() {
		
		List<OrderAcme> listaOrder = this.orderService.findAll();

		if (listaOrder == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<OrderAcme>>(listaOrder, HttpStatus.OK);

	}

	// ENDPOINT: PESQUISAR POR ID
	@RequestMapping(value = "/order/findById", method = RequestMethod.POST)
	public ResponseEntity<OrderAcme> findById(@RequestParam("id") Integer id) {

		Optional<OrderAcme> order = this.orderService.findById(id);

		if (order == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<OrderAcme>(order.get(), HttpStatus.OK);

	}

	// ENDPOINT: PESQUISAR POR STATUS
	@RequestMapping(value = "/order/findByStatus", method = RequestMethod.POST)
	public ResponseEntity<List<OrderAcme>> findByStatus(@RequestParam("status") String status) {

		List<OrderAcme> listaOrder = this.orderService.findByStatus(status);

		if (listaOrder == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<OrderAcme>>(listaOrder, HttpStatus.OK);

	}

}
