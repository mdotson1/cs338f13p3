package com.cs388f13p1;

public class UniversityPerson extends Person {
	
	private int id;

	public UniversityPerson(final ContactInformation ci, final int id, 
			final String dob) {
		
		super(ci, dob);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
