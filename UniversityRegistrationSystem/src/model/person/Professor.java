package model.person;

import model.time.Date;

public class Professor extends UniversityPerson {

	protected String department;
	
	public Professor(Address homeAddr, Address workAddr,
			PhoneNumbers phoneNums, String fName, String lName,
			int id, Date dob, String dept) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName,
				dob, id);
		department = dept;
	}
}
