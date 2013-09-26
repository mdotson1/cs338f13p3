package model.person;

import java.util.ArrayList;
import java.util.List;

import model.billing.Payment;
import model.course.Course;
import model.course.CourseOffering;
import model.time.Date;


public class Student extends Person {
	
	private List<CourseOffering> courses;
	private int currentBalance;
	private List<Payment> paymentHistory;
	private boolean hasRegistered;
	
	public Student(Address homeAddr, Address workAddr,
			PhoneNumbers phoneNums, String fName, String lName,
			long social, Date dob) {
		super(homeAddr, workAddr, phoneNums, fName, lName,
				social, dob);
		courses = new ArrayList<CourseOffering>();
		paymentHistory = new ArrayList<Payment>();
		hasRegistered = false;
	}
	
	public boolean hasRegistered() {
		return hasRegistered;
	}
	
	public int getCurrentBalance() {
		return currentBalance;
	}
	
	public void bill() {
		for (int i = 0; i < courses.size(); i++) {
			currentBalance =+ courses.get(i).getCost();
		}
	}
	
	public List<Payment> getPaymentHistory() {
		return paymentHistory;
	}
	
	public void payBalance(Payment payment) {
		paymentHistory.add(payment);
		currentBalance -= payment.getPaymentAmount();
	}
	
	// true = added, false = not added
	public boolean enrollInCourse(CourseOffering course) {
		if (courses.size() == 4) {
			return false;
		}
		else
		{
			if (courses.contains(course)) {
				return false;
			}
			else
			{
				courses.add(course);
				return true;
			}
		}
	}
	
	// true = dropped, false = not dropped
	public boolean dropCourse(CourseOffering course) {
		if (courses.contains(course)) {
			courses.remove(course);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<CourseOffering> getCourses() {
		return courses;
	}

}
