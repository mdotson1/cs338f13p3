package model.person;

public class Address {

	private int streetNumber;
	private String streetName;
	private int apartmentNumber;
	private String city;
	private String state;
	private int zipCode;
	
	public Address(int num, String name, int apt, String city, String state, int zip) {
		streetNumber = num;
		streetName = name;
		apartmentNumber = apt;
		this.city = city;
		this.state = state;
		zipCode = zip;
	}
}
