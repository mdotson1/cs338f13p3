package model.person;

import model.time.Date;

public class Person extends ContactInformation {
	
	protected int ssn;
	protected Date dateOfBirth;
	
	public Person(Address homeAddr, Address workAddr,
			PhoneNumbers phoneNums, String fName, String lName,
			int social, Date dob) {
		homeAddress = homeAddr;
		workAddress = workAddr;
		phones = phoneNums;
		firstName = fName;
		lastName = lName;
		ssn = social;
		dateOfBirth = dob;
	}
}
