package model.person;

public class ContactInformation {
	
	protected Address homeAddress;
	protected Address workAddress;
	protected PhoneNumbers phones;
	protected String firstName;
	protected String lastName;
	
	public ContactInformation(Address homeAddr, Address workAddr, 
			PhoneNumbers phoneNums, String fName, String lName) {
		homeAddress = homeAddr;
		workAddress = workAddr;
		phones = phoneNums;
		firstName = fName;
		lastName = lName;
	}

}
