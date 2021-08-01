package com.rest.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private String street;
	private String city;
	private String region;
	private String postalCode;
	private String countryCode;
	private String country;

}
