package com.cs388f13p1;

public class UniversityPerson extends Person {
	
	protected int id;

	public UniversityPerson(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName, 
			final String lName, final Date dob, final int id) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName, dob);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
