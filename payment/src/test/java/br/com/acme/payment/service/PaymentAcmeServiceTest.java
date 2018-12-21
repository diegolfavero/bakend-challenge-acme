package br.com.acme.payment.service;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.acme.payment.model.PaymentAcme;
import br.com.acme.payment.repository.PaymentAcmeRepository;

@RunWith(SpringRunner.class)
public class PaymentAcmeServiceTest {

	@InjectMocks
	private PaymentAcmeService paymentAcmeService;

	@Mock
	private PaymentAcmeRepository paymentAcmeRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final Integer ID = Integer.parseInt("1");
	private final String paymDate = "2018-01-01";
	private final String status = "AGUARDANDO";
	private final Long numberCard = Long.parseLong("123456789");

	private PaymentAcme paymentAcme;
	
	private List<PaymentAcme> listpaymentAcme;

	@Before
	public void setup() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date dataFormatada = dateFormat.parse(paymDate);
			paymentAcme = new PaymentAcme(ID, status, numberCard, dataFormatada);
			listpaymentAcme = new ArrayList<PaymentAcme>();
			listpaymentAcme.add(paymentAcme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void save() {
		Mockito.when(paymentAcmeRepository.save(Mockito.any(PaymentAcme.class))).thenReturn(paymentAcme);
		PaymentAcme retorno = paymentAcmeService.save(paymentAcme);
		assertNotNull(retorno);
		assertNotNull(retorno.getId());
	}

	@Test
	public void findAll() {
		Mockito.when(paymentAcmeRepository.findAll()).thenReturn(listpaymentAcme);
		List<PaymentAcme> retorno = paymentAcmeService.findAll();
		assertNotNull(retorno);
	}
	
}
