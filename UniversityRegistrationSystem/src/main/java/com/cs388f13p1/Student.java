package main.java.com.cs388f13p1;

import java.util.ArrayList;
import java.util.List;


public class Student extends UniversityPerson {
	
	private List<CourseOffering> courses;
	private int currentBalance;
	private List<Payment> paymentHistory;
	private boolean hasRegistered;
	
	public Student(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName, 
			final String lName, final Date dob, final int id) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName,
				dob, id);
		
		courses = new ArrayList<CourseOffering>();
		paymentHistory = new ArrayList<Payment>();
		hasRegistered = false;
	}
	
	public final boolean hasRegistered() {
		return hasRegistered;
	}
	
	public final int getCurrentBalance() {
		return currentBalance;
	}
	
	public void bill() {
		for (int i = 0; i < courses.size(); i++) {
			currentBalance =+ courses.get(i).getCost();
		}
	}
	
	public final List<Payment> getPaymentHistory() {
		return paymentHistory;
	}
	
	public void payBalance(final Payment payment) {
		paymentHistory.add(payment);
		currentBalance -= payment.getPaymentAmount();
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
	
	public List<CourseOffering> getCourses() {
		return courses;
	}

}
