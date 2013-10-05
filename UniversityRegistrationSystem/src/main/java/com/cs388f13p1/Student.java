package com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Student extends UniversityPerson {
	
	private List<CourseOffering> courses;
	private double currentBalance;
	private List<Payment> paymentHistory;
	private boolean hasRegistered;
	
	public Student(final ContactInformation ci, final int id, final String dob) {
		
		super(ci, id, dob);
		
		courses = new ArrayList<CourseOffering>();
		paymentHistory = new ArrayList<Payment>();
		hasRegistered = false;
	}
	
	public boolean hasRegistered() {
		return hasRegistered;
	}
	
	public void register() {
		hasRegistered = true;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public void setCurrentBalance(double balance) {
		currentBalance = balance;
	}
	
	public final List<Payment> getPaymentHistory() {
		return paymentHistory;
	}
	
	public void addCourse(final CourseOffering course) {
		courses.add(course);
	}
	
	public int getNumCourses() {
		return courses.size();
	}
	
	public void removeCourse(final CourseOffering course) {
		courses.remove(course);
	}
	
	public Iterator<CourseOffering> getCourses() {
		return courses.iterator();
	}
	
	public boolean equals(Student s) {
		return this.getId() == s.getId();
	}

}
