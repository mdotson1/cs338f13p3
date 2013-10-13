package com.cs388f13p2.model.person;

public class Professor extends UniversityPerson {

	protected String department;
	
	public Professor(ContactInformation ci, final int id, final String dob, 
			final String dept) {
		
		super(ci, id, dob);
		department = dept;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String dpt) {
		department = dpt;
	}
}
