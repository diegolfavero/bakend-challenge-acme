package br.com.acme.order.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.acme.order.model.OrderItemAcme;
import br.com.acme.order.repository.OrderItemAcmeRepository;

@RunWith(SpringRunner.class)
public class OrderItemAcmeServiceTest {

	@InjectMocks
	private OrderItemAcmeService orderItemAcmeService;

	@Mock
	private OrderItemAcmeRepository orderItemAcmeRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final Integer ID = Integer.parseInt("1");
	private final String description = "description";
	private final Double price = 0D;
	private final Integer quantity = Integer.parseInt("1");

	private OrderItemAcme orderItemAcme;
	
	private List<OrderItemAcme> listorderItemAcme;

	@Before
	public void setup() {
		orderItemAcme = new OrderItemAcme(ID, description, price, quantity);
		listorderItemAcme = new ArrayList<OrderItemAcme>();
		listorderItemAcme.add(orderItemAcme);
	}

	@Test
	public void save() {
		Mockito.when(orderItemAcmeRepository.save(Mockito.any(OrderItemAcme.class))).thenReturn(orderItemAcme);
		OrderItemAcme retorno = orderItemAcmeService.save(orderItemAcme);
		assertNotNull(retorno);
		assertNotNull(retorno.getId());
	}

	@Test
	public void findAll() {
		Mockito.when(orderItemAcmeRepository.findAll()).thenReturn(listorderItemAcme);
		List<OrderItemAcme> retorno = orderItemAcmeService.findAll();
		assertNotNull(retorno);
	}

	@Test
	public void findById() {
		Mockito.when(orderItemAcmeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(orderItemAcme));
		Optional<OrderItemAcme> retorno = orderItemAcmeService.findById(ID);
		assertNotNull(retorno);
	}

	@Test
	public void findByIdInvalid() {
		Mockito.when(orderItemAcmeRepository.findById(Mockito.anyInt())).thenReturn(null);
		Optional<OrderItemAcme> retorno = orderItemAcmeService.findById(ID);
		assertNull(retorno);
	}
	
}
