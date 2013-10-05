package com.cs388f13p1;

public class ContactInformation {
	
	protected Address homeAddress;
	protected Address workAddress;
	protected PhoneNumbers phones;
	protected String firstName;
	protected String lastName;
	
	public ContactInformation(final Address homeAddr, final Address workAddr, 
			final PhoneNumbers phoneNums, final String fName,
			final String lName) {
		homeAddress = homeAddr;
		workAddress = workAddr;
		phones = phoneNums;
		firstName = fName;
		lastName = lName;
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(final Address homeAddr) {
		this.homeAddress = homeAddr;
	}
	
	public Address getWorkAddress() {
		return workAddress;
	}
	
	public void setWorkAddress(final Address workAddr) {
		this.workAddress = workAddr;
	}
	
	public PhoneNumbers getPhones() {
		return phones;
	}
	
	public void setPhones(PhoneNumbers phoneNums) {
		phones = phoneNums;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String fName) {
		firstName = fName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lName) {
		lastName = lName;
	}

}
