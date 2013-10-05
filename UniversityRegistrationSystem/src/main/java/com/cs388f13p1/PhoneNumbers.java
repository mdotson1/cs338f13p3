package com.cs388f13p1;

public class PhoneNumbers {

	private String workPhone;
	private String homePhone;
	private String cellPhone;
	
	public PhoneNumbers(final String work, final String home, 
			final String cell) {
		setWorkPhone(work);
		setHomePhone(home);
		setCellPhone(cell);
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
