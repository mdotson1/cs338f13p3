package com.cs388f13p1;

public class Address {

	private int streetNumber;
	private String streetName;
	private int apartmentNumber;
	private String city;
	private String state;
	private int zipCode;
	
	public Address(final int num, final String name, final int apt, 
			final String city, final String state, final int zip) {
		streetNumber = num;
		streetName = name;
		apartmentNumber = apt;
		this.city = city;
		this.state = state;
		zipCode = zip;
	}
}
