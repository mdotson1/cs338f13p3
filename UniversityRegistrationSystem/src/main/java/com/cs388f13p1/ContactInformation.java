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

}
