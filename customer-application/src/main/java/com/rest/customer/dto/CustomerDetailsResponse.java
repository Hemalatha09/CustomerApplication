package com.rest.customer.dto;

import com.rest.customer.model.Customer;

public class CustomerDetailsResponse {
	private Customer details;

	public Customer getDetails() {
		return details;
	}

	public void setDetails(Customer details) {
		this.details = details;
	}

}
