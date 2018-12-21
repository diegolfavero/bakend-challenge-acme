package br.com.acme.store.service;

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

import br.com.acme.store.model.StoreAcme;
import br.com.acme.store.repository.StoreAcmeRepository;

@RunWith(SpringRunner.class)
public class StoreAcmeServiceTest {

	@InjectMocks
	private StoreAcmeService storeAcmeService;

	@Mock
	private StoreAcmeRepository storeAcmeRepository;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final Integer ID = Integer.parseInt("1");
	private final String address = "address";
	private final String name = "name";

	private StoreAcme storeAcme;
	
	private List<StoreAcme> listStoreAcme;

	@Before
	public void setup() {
		storeAcme = new StoreAcme(ID, name, address);
		listStoreAcme = new ArrayList<StoreAcme>();
		listStoreAcme.add(storeAcme);
	}

	@Test
	public void save() {
		Mockito.when(storeAcmeRepository.save(Mockito.any(StoreAcme.class))).thenReturn(storeAcme);
		StoreAcme retorno = storeAcmeService.save(storeAcme);
		assertNotNull(retorno);
		assertNotNull(retorno.getId());
	}

	@Test
	public void findAll() {
		Mockito.when(storeAcmeRepository.findAll()).thenReturn(listStoreAcme);
		List<StoreAcme> retorno = storeAcmeService.findAll();
		assertNotNull(retorno);
	}
	
	@Test
	public void findById() {
		Mockito.when(storeAcmeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(storeAcme));
		Optional<StoreAcme> retorno = storeAcmeService.findById(ID);
		assertNotNull(retorno);
	}

	@Test
	public void findByIdInvalid() {
		Mockito.when(storeAcmeRepository.findById(Mockito.anyInt())).thenReturn(null);
		Optional<StoreAcme> retorno = storeAcmeService.findById(ID);
		assertNull(retorno);
	}
	
}
