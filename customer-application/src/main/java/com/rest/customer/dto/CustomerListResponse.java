package com.rest.customer.dto;

import java.util.List;

import com.rest.customer.model.Customer;

public class CustomerListResponse {

	private int total;
	private List<Customer> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Customer> getItems() {
		return items;
	}

	public void setItems(List<Customer> items) {
		this.items = items;
	}

}
