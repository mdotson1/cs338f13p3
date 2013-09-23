package model.person;

import java.util.ArrayList;
import java.util.List;

import model.time.Date;


public class Student extends Person {
	
	private List<CourseOffering> courses;
	private int currentBalance;
	private List<Payment> paymentHistory;
	
	public Student(Address homeAddr, Address workAddr,
			PhoneNumbers phoneNums, String fName, String lName,
			int social, Date dob) {
		super(homeAddr, workAddr, phoneNums, fName, lName,
				social, dob);
		courses = new ArrayList<CourseOferring>();
		paymentHistory = new ArrayList<Payment>();
	}

}
