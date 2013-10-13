package com.cs388f13p2.model.person;

public class Student extends UniversityPerson {
	
	private double currentBalance;
	
	public Student(final ContactInformation ci, final int id, final String dob,
			final double currentBalance) {
		
		super(ci, id, dob);
		
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

}
