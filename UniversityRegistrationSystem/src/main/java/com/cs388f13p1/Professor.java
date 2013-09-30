package main.java.com.cs388f13p1;

public class Professor extends UniversityPerson {

	protected String department;
	
	public Professor(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phoneNums, final String fName, 
			final String lName, final int id, final Date dob, 
			final String dept) {
		
		super(homeAddr, workAddr, phoneNums, fName, lName,
				dob, id);
		department = dept;
	}
}
