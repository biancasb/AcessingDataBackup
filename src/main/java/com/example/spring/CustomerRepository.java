package com.example.spring;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByLastName(String lastName);
	
	Collection<Customer> findAll() throws DataAccessException;
	
	Customer findById(long id);

}
