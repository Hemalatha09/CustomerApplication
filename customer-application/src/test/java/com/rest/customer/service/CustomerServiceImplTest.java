package com.rest.customer.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.customer.dto.CustomerListResponse;
import com.rest.customer.dto.CustomerRequest;
import com.rest.customer.dto.CustomerResponse;
import com.rest.customer.model.Address;
import com.rest.customer.model.Customer;
import com.rest.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerServiceImpl.class })
public class CustomerServiceImplTest {
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	@MockBean
	CustomerRepository customerRepository;
	
	CustomerRequest customerRequest;
	
	
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
	public void getCustomerListServiceTest() {
		List<Customer> customerList = new ArrayList();
		Customer customer = customerEntity();
		customerList.add(customer);
		when(customerRepository.findAll()).thenReturn(customerList);
		CustomerListResponse response = customerServiceImpl.getCustomerList();
		assertEquals(1,response.getTotal());
	}
	
	@Test
	public void updateCustomerServiceTest() {
		Customer customer = customerEntity();
		customer.setId(3);		
		when(customerRepository.findById(Matchers.anyInt())).thenReturn(Optional.of(customer));
		CustomerResponse customerResponse = customerServiceImpl.updateCustomer(customerRequest);
		assertEquals(3,customerResponse.getId());
	}
	
	
	private Customer customerEntity()
	{
		Customer customer = new Customer();				
		customer.setFirstName("TEST");
		customer.setLastName("K");
		customer.setCompany("TATA");
		customer.setDesignation("SOFTWARE ENGINEER");
		customer.setRole(2);
		customer.setEmail("test1@gmail.com");
		customer.setMobile("1234567098");
		customer.setStatus("NEW");		
		customer.setCreatedby(1);	
	       Address address = new Address();
		address.setCity("BANGALORE");
		address.setCountry("INDIA");
		address.setPostalCode("560048");
		address.setCountryCode("IN");
		address.setRegion("IN");
		address.setStreet("HOODI");
		customer.setAddress(address);
		return customer;
		
	}

}
