package com.rest.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.customer.dto.CustomerListResponse;
import com.rest.customer.dto.CustomerRequest;
import com.rest.customer.dto.CustomerResponse;
import com.rest.customer.model.Customer;
import com.rest.customer.service.CustomerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	/**
	 * API to add new customer
	 * 
	 * 
	 * @return CustomerResponse
	 */
	
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
		log.info("inside CustomerController: addCustomer()");
		// return ResponseEntity.ok(userService.signUp(signUpRequest));
		CustomerResponse response = new CustomerResponse();
		try {
			response = customerService.addCustomer(customerRequest);
		} catch (Exception e) {
			log.error("Error while adding customer: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * API to get the Customer details by its Id
	 * 
	 * @param id
	 * @return Customer details
	 */

	@Cacheable(value = "customer", key = "#id")
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);

	}

	@Cacheable(value = "customers")
	@GetMapping("/customerList")
	public CustomerListResponse getCustomerList() {
		return customerService.getCustomerList();
	}

	/**
	 * API to get the Customer details by its Id
	 * 
	 * @param id
	 * @return CustomerResponse
	 */

	@CrossOrigin
	@PutMapping("/update")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest) {
		log.info("inside CustomerController: updateCustomer()");
		// return ResponseEntity.ok(userService.signUp(signUpRequest));
		CustomerResponse response = new CustomerResponse();
		try {
			response = customerService.updateCustomer(customerRequest);
		} catch (Exception e) {
			log.error("Error while adding customer: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	/**
	 * API to get the delete details by its Id
	 * 
	 * @param id
	 * @return CustomerResponse
	 */

	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerResponse> deleteCustomerById(@PathVariable int id) {
		return ResponseEntity.ok(customerService.deleteCustomerById(id));
	}

}
