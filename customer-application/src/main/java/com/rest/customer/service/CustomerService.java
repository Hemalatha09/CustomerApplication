package com.rest.customer.service;

import com.rest.customer.dto.CustomerListResponse;
import com.rest.customer.dto.CustomerRequest;
import com.rest.customer.dto.CustomerResponse;
import com.rest.customer.exceptions.CustomerNotFound;
import com.rest.customer.model.Customer;

public interface CustomerService {

	public CustomerResponse addCustomer(CustomerRequest customerōRequest);

	public CustomerResponse updateCustomer(CustomerRequest customerōRequest);

	public Customer getCustomerById(int id) throws CustomerNotFound;

	public CustomerListResponse getCustomerList();

	public CustomerResponse deleteCustomerById(int id);

}
