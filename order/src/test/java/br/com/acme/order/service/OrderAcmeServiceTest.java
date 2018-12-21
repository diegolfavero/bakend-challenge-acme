package br.com.acme.order.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.acme.order.model.OrderAcme;
import br.com.acme.order.repository.OrderAcmeRepository;

@RunWith(SpringRunner.class)
public class OrderAcmeServiceTest {

	@InjectMocks
	private OrderAcmeService orderAcmeService;

	@Mock
	private OrderAcmeRepository orderAcmeRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final Integer ID = Integer.parseInt("1");
	private final String address = "address";
	private final String date = "2018-01-01";
	private final String status = "AGUARDANDO";

	private OrderAcme orderAcme;
	
	private List<OrderAcme> listOrderAcme;

	@Before
	public void setup() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date dataFormatada = dateFormat.parse(date);
			orderAcme = new OrderAcme(ID, address, dataFormatada, status);
			listOrderAcme = new ArrayList<OrderAcme>();
			listOrderAcme.add(orderAcme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void save() {
		Mockito.when(orderAcmeRepository.save(Mockito.any(OrderAcme.class))).thenReturn(orderAcme);
		OrderAcme retorno = orderAcmeService.save(orderAcme);
		assertNotNull(retorno);
		assertNotNull(retorno.getId());
	}

	@Test
	public void findAll() {
		Mockito.when(orderAcmeRepository.findAll()).thenReturn(listOrderAcme);
		List<OrderAcme> retorno = orderAcmeService.findAll();
		assertNotNull(retorno);
	}

	@Test
	public void findById() {
		Mockito.when(orderAcmeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(orderAcme));
		Optional<OrderAcme> retorno = orderAcmeService.findById(ID);
		assertNotNull(retorno);
	}
	
	@Test
	public void findByStatus() {
		Mockito.when(orderAcmeRepository.findByStatus(Mockito.anyString())).thenReturn(listOrderAcme);
		List<OrderAcme> retorno = orderAcmeService.findByStatus(status);
		assertNotNull(retorno);
	}

	@Test
	public void findByIdInvalid() {
		Mockito.when(orderAcmeRepository.findById(Mockito.anyInt())).thenReturn(null);
		Optional<OrderAcme> retorno = orderAcmeService.findById(ID);
		assertNull(retorno);
	}
	
}
