package com.cs388f13p1;

// place in database under person
public class ContactInformation {
	
	private Address homeAddress;
	private Address workAddress;
	private String firstName;
	private String lastName;
	private String workPhone;
	private String homePhone;
	private String cellPhone;
	
	public ContactInformation(final Address homeAddr, final Address workAddr, 
			final String fName, final String lName, String workPhone, 
			String homePhone, String cellPhone) {
		homeAddress = homeAddr;
		workAddress = workAddr;
		firstName = fName;
		lastName = lName;
		this.workPhone = workPhone;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(final Address homeAddr) {
		this.homeAddress = homeAddr;
	}
	
	public Address getWorkAddress() {
		return workAddress;
	}
	
	public void setWorkAddress(final Address workAddr) {
		this.workAddress = workAddr;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String fName) {
		firstName = fName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lName) {
		lastName = lName;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

}
