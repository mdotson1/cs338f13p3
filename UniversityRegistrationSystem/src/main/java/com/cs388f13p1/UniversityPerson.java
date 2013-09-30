package com.cs388f13p1;

public class UniversityPerson extends Person {
	
	protected int idNumber;

	public UniversityPerson(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName, 
			final String lName, final Date dob, final int id) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName, dob);
		idNumber = id;
	}
	
	public final int getId() {
		return idNumber;
	}

}
