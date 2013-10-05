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

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
}
