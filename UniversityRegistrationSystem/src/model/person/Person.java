package model.person;

import model.time.Date;

public class Person extends ContactInformation {
	
	protected Date dateOfBirth;
	
	public Person(Address homeAddr, Address workAddr,
			PhoneNumbers phoneNums, String fName, String lName,
			Date dob) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName);
		
		dateOfBirth = dob;
	}
}
