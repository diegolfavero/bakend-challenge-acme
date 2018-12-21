package br.com.acme.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.order.model.OrderItemAcme;
import br.com.acme.order.repository.OrderItemAcmeRepository;

@RestController
public class OrderItemAcmeController {
	
	@Autowired
	private OrderItemAcmeRepository orderItemAcmeRepository;

	// ENDPOINT: INSERIR ORDER ITEM
	@RequestMapping(value = "/orderItem/insertOrderItem", method = RequestMethod.POST)
	public ResponseEntity<String> insertOrderItem(@RequestParam("description") String description,
		@RequestParam("price") String price, @RequestParam("quantity") Integer quantity) {
		try {
			Double priceFormatado = Double.parseDouble(price);
			OrderItemAcme orderItem = new OrderItemAcme(null, description, priceFormatado, quantity);
			this.orderItemAcmeRepository.save(orderItem);
			return new ResponseEntity<String>("Order Item inserted successfully." + orderItem.getDescription(), HttpStatus.OK);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error to insert Order Item.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ENDPOINT: PESQUISAR TODOS
	@RequestMapping(value = "/orderItem/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<OrderItemAcme>> findAll() {

		List<OrderItemAcme> listaOrderItem = this.orderItemAcmeRepository.findAll();

		if (listaOrderItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<OrderItemAcme>>(listaOrderItem, HttpStatus.OK);

	}
	
}
