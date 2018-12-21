package br.com.acme.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.store.model.StoreAcme;
import br.com.acme.store.service.StoreAcmeService;

@RestController
public class StoreAcmeController {

	@Autowired
	private StoreAcmeService storeAcmeService;

	// ENDPOINT: INSERIR STORE
	@RequestMapping(value = "/store/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertStore(@RequestParam("name") String name,
		@RequestParam("address") String address) {
		
		try {
			
			StoreAcme store = new StoreAcme(null, name, address);
			this.storeAcmeService.save(store);
			return new ResponseEntity<String>("Store inserted successfully." + store.getName(), HttpStatus.OK);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error to insert Store.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// ENDPOINT: ATUALIZAR STORE
	@RequestMapping(value = "/store/update", method = RequestMethod.POST)
	public ResponseEntity<String> updateStore(@RequestParam("id") Integer id, @RequestParam("name") String name,
		@RequestParam("address") String address) {
		
		try {
			
			Optional<StoreAcme> store = this.storeAcmeService.findById(id);
			
			if (store != null) {
				
				StoreAcme storeUpdate = store.get();
				storeUpdate.setName(name);
				storeUpdate.setAddress(address);
				this.storeAcmeService.save(storeUpdate);
				
				return new ResponseEntity<String>("Store updated successfully.", HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("Store not found.", HttpStatus.FOUND);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error to update Store.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// ENDPOINT: PESQUISAR TODOS
	@RequestMapping(value = "/store/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<StoreAcme>> findAll() {
		
		List<StoreAcme> listStore = this.storeAcmeService.findAll();
		
		if (listStore == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<StoreAcme>>(listStore, HttpStatus.OK);
		
	}

	// ENDPOINT: PESQUISAR POR ID
	@RequestMapping(value = "/store/findById", method = RequestMethod.POST)
	public ResponseEntity<String> findById(@RequestParam("id") Integer id) {
		
		try {
			
			Optional<StoreAcme> store = this.storeAcmeService.findById(id);
			
			if (store != null) {
				return new ResponseEntity<String>("Store: " + store.get().getName() + ".", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Store not found.", HttpStatus.FOUND);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error to find Store.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	// ENDPOINT: EXCLUIR STORE
	@RequestMapping(value = "/store/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteStore(@RequestParam("id") Integer id) {
		
		try {
			
			Optional<StoreAcme> store = this.storeAcmeService.findById(id);
			
			if (store != null) {
				
				this.storeAcmeService.delete(store.get());
				return new ResponseEntity<String>("Store deleted successfully.", HttpStatus.OK);
				
			} else {
				return new ResponseEntity<String>("Store not found!", HttpStatus.FOUND);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("Error to find Store.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
