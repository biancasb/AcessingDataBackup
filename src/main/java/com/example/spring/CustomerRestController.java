package com.example.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepository repository;

	@GetMapping("/api/customers")
	public ResponseEntity<Collection<Customer>> getCustomers() {
		Collection<Customer> lista = repository.findAll(); 
		if(lista.isEmpty()){
			return new ResponseEntity<Collection<Customer>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Customer>>(lista, HttpStatus.OK);
	}	
}
