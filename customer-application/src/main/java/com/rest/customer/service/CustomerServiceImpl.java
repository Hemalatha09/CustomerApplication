package com.rest.customer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.customer.dto.CustomerListResponse;
import com.rest.customer.dto.CustomerRequest;
import com.rest.customer.dto.CustomerResponse;
import com.rest.customer.exceptions.CustomerNotFound;
import com.rest.customer.model.Address;
import com.rest.customer.model.Customer;
import com.rest.customer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	
	static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	@Transactional
	public CustomerResponse addCustomer(CustomerRequest customerRequest) {
		// TODO Auto-generated method stub
	Customer customer = new Customer();	  
	customer.setFirstName(customerRequest.getFirstName());
	customer.setLastName(customerRequest.getLastName());
	customer.setCompany(customerRequest.getCompany());
	customer.setDesignation(customerRequest.getDesignation());
	customer.setRole(customerRequest.getRole());
	customer.setEmail(customerRequest.getEmail());
	customer.setMobile(customerRequest.getMobile());
	customer.setStatus(customerRequest.getStatus());
	customer.setCreated(new Date());
	customer.setCreatedby(1);	
       Address address = new Address();
	address.setCity(customerRequest.getAddress().getCity());
	address.setCountry(customerRequest.getAddress().getCity());
	address.setPostalCode(customerRequest.getAddress().getPostalCode());
	address.setCountryCode(customerRequest.getAddress().getCountryCode());
	address.setRegion(customerRequest.getAddress().getRegion());
	address.setStreet(customerRequest.getAddress().getStreet());
	customer.setAddress(address);
	try {
	Customer save = customerRepo.save(customer);	
	}catch(Exception e) {
		logger.error("Exception occured while saving customer data {}",e.getMessage());
	}
	CustomerResponse response = new CustomerResponse();
	response.setId(customer.getId());
	response.setMessage("Customer added Successfully");	
	 return response;
	}

	@Override
	public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
		// TODO Auto-generated method stub
		
		Customer customer = customerRepo.findById(customerRequest.getId())
				.orElseThrow(() -> new CustomerNotFound("Customer ID Not Found"));
		
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setCompany(customerRequest.getCompany());
		customer.setDesignation(customerRequest.getDesignation());
		customer.setRole(customerRequest.getRole());
		customer.setEmail(customerRequest.getEmail());
		customer.setMobile(customerRequest.getMobile());
		customer.setStatus(customerRequest.getStatus());
		customer.setUpdated(new Date());
		customer.setUpdatedby(8);	
	       Address address = new Address();
		address.setCity(customerRequest.getAddress().getCity());
		address.setCountry(customerRequest.getAddress().getCity());
		address.setPostalCode(customerRequest.getAddress().getPostalCode());
		address.setCountryCode(customerRequest.getAddress().getCountryCode());
		address.setRegion(customerRequest.getAddress().getRegion());
		address.setStreet(customerRequest.getAddress().getStreet());
		customer.setAddress(address);
		try {
		Customer save = customerRepo.save(customer);
		logger.info("Updated"+save);
		}catch(Exception e) {
			logger.error("Exception Occured while updating customer data {}",e.getMessage());
		}
		CustomerResponse response = new CustomerResponse();
		response.setId(customer.getId());
		response.setMessage("Customer details updated successfully");
		
		 return response;
		}

		

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		Customer details = null;
		logger.info("Inside CustomerServiceImpl : getCustomerById()");
		details = customerRepo.findById(id)
				.orElseThrow(() -> new CustomerNotFound("Customer not found"));

		return details;
	}


	 @Override
	    public CustomerResponse deleteCustomerById(int id) {
	    	Customer customer = customerRepo.findById(id)
					.orElseThrow(() -> new CustomerNotFound("Customer ID Not Found" +id));     
	           customerRepo.delete(customer);
	           CustomerResponse response = new CustomerResponse();
	           response.setId(id);
	           response.setMessage("customer deleted successfully");           
	        return response;

	    }


	 @Override
		public CustomerListResponse getCustomerList() {
			// TODO Auto-generated method stub
			CustomerListResponse response = new CustomerListResponse();
			List<Customer> customerList = customerRepo.findAll();
			response.setTotal(customerList.size());
			response.setItems(customerList);
			return response;
		}   

	
	
}
