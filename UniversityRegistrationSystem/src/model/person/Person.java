package model.person;

import model.time.Date;

public class Person extends ContactInformation {
	
	protected Date dateOfBirth;
	
	public Person(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName,
			final String lName, final Date dob) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName);
		
		dateOfBirth = dob;
	}
}
