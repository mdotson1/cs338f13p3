package com.cs388f13p2.model.person;

public class UniversityPerson extends Person {
	
	protected int id;

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
