package models.person;


// place in database under person
public class ContactInformation {
	
	private String homeAddress;
	private String workAddress;
	private String firstName;
	private String lastName;
	private String workPhone;
	private String homePhone;
	private String cellPhone;
	
	public ContactInformation(final String homeAddr, final String workAddr, 
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
	
	public String getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(final String homeAddr) {
		this.homeAddress = homeAddr;
	}
	
	public String getWorkAddress() {
		return workAddress;
	}
	
	public void setWorkAddress(final String workAddr) {
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
