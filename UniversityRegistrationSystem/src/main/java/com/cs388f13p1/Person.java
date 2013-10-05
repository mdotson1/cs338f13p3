package com.cs388f13p1;

public class Person extends ContactInformation {
	
	protected Date dateOfBirth;
	
	public Person(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName,
			final String lName, final Date dob) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName);
		
		dateOfBirth = dob;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dob) {
		dateOfBirth = dob;
	}
}
