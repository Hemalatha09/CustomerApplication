package com.rest.customer.controller;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.customer.dto.CustomerListResponse;
import com.rest.customer.dto.CustomerRequest;
import com.rest.customer.dto.CustomerResponse;
import com.rest.customer.exceptions.CustomerNotFound;
import com.rest.customer.model.Address;
import com.rest.customer.model.Customer;
import com.rest.customer.repository.CustomerRepository;
import com.rest.customer.service.CustomerService;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerController.class })
public class CustomerControllerTest {
	
	@Autowired
	CustomerController customerController;
	
	@MockBean
	CustomerRepository customerRepository;
	
	@MockBean
	CustomerService customerService;
	
	private CustomerRequest customerRequest;
	
	@Before
	public void setUp() throws Exception {
		
		customerRequest = new CustomerRequest();		
		customerRequest.setFirstName("TEST");
		customerRequest.setLastName("K");
		customerRequest.setCompany("TATA");
		customerRequest.setDesignation("SOFTWARE ENGINEER");
		customerRequest.setRole(2);
		customerRequest.setEmail("test1@gmail.com");
		customerRequest.setMobile("1234567098");
		customerRequest.setStatus("NEW");		
		customerRequest.setCreatedby(1);	
	       Address address = new Address();
		address.setCity("BANGALORE");
		address.setCountry("INDIA");
		address.setPostalCode("560048");
		address.setCountryCode("IN");
		address.setRegion("IN");
		address.setStreet("HOODI");
		customerRequest.setAddress(address);
		
	}

	
	
	@Test
	public void updateCustomerTest() {
		ResponseEntity<CustomerResponse> responseEntity = customerController.updateCustomer(customerRequest);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}	
	
	
	@Test
	public void addCustomerTest() {
		ResponseEntity<CustomerResponse> responseEntity = customerController.addCustomer(customerRequest);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}


	@Test
	public void deleteCustomerByIdTest() {
		ResponseEntity<CustomerResponse>  response= customerController.deleteCustomerById(3);
		assertNotNull(response);	
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
    public void addCustomerTest_Exception(){      
        when(customerService.addCustomer (customerRequest)).thenThrow (PersistenceException.class);
        ResponseEntity responseEntity = customerController.addCustomer (customerRequest);
        assertNotNull (responseEntity);
        assertEquals (HttpStatus.INTERNAL_SERVER_ERROR,responseEntity.getStatusCode ());
    }
	
	
	


}
