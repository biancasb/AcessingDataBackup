package com.example.spring;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerTest {

	@Autowired
	private CustomerRestController CrestController;
	
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	CommandLineRunner comandLineRunner;
	
	private MockMvc mvc;
	
	
	
	private List<Customer> cust;

	@BeforeEach
	public void initCustomer() {
		this.mvc = MockMvcBuilders.standaloneSetup(CrestController).setControllerAdvice().build();
		
		cust = new ArrayList<Customer>();
		
		Customer CustomerWw = new Customer();
		CustomerWw.setId(1L);
		CustomerWw.setFirstName("Walter");
		CustomerWw.setLastName("White");
		cust.add(CustomerWw);
	}

	@Test
	void testGetAllCustomersSuccess() throws Exception {
		// TODO O MOCK DO REPOSITORIO VAI RETORNAR UM OBJETO BREAKING BAD
		// TODO DEVE RETORNAR UM ID1 E O FIRST NAME walter white
		//cust.remove(0);
		given(this.customerRepository.findAll()).willReturn(cust);
		mvc.perform(MockMvcRequestBuilders.get("/api/customers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.[0].id").value(1))
				.andExpect(jsonPath("$.[0].firstName").value("Walter"))
				.andExpect(jsonPath("$.[0].lastName").value("White"));
	}

	@Test
	void testGetAllCustomersNotFound() throws Exception {
		// TODO QUANDO EX O TESTE O MOCK VAI DIZER QUE O BANCO ESTA VAZIO
		cust.clear();
		given(this.customerRepository.findAll()).willReturn(cust);
		mvc.perform(MockMvcRequestBuilders.get("/api/customers")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
