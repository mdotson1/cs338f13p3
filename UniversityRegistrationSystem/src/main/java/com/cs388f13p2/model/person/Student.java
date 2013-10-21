package com.cs388f13p2.model.person;

public class Student extends UniversityPerson {
	
	private double currentBalance;
	
	// when retrieving from database, has id
	public Student(final ContactInformation ci, final int id, final String dob,
			final double currentBalance) {
		
		super(ci, id, dob);
		
		this.currentBalance = currentBalance;
	}
	
	// when creating before adding to database
	public Student(final ContactInformation ci, final String dob,
			final double currentBalance) {
		
		super(ci, -1, dob);
		
		this.currentBalance = currentBalance;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public void setCurrentBalance(double balance) {
		currentBalance = balance;
	}
	
	public boolean equals(Student s) {
		return this.getId() == s.getId();
	}
	
	@Override
	public String toString() {
		return id + ": " + contactInformation.getFirstName() + " " + contactInformation.getLastName();
	}

}
