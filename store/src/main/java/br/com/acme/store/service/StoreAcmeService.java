package br.com.acme.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.store.model.StoreAcme;
import br.com.acme.store.repository.StoreAcmeRepository;

@Service
public class StoreAcmeService {

	@Autowired
	private StoreAcmeRepository storeAcmeRepository;
	
	public StoreAcme save(StoreAcme storeAcme) {
		return this.storeAcmeRepository.save(storeAcme);
	}

	public List<StoreAcme> findAll() {
		return this.storeAcmeRepository.findAll();
	}

	public Optional<StoreAcme> findById(Integer id) {
		return this.storeAcmeRepository.findById(id);
	}

	public void delete(StoreAcme storeAcme) {
		this.storeAcmeRepository.delete(storeAcme);
	}
	
}