package models.person;

public class Professor extends UniversityPerson {

	protected String department;

	// when retrieving from database, has id
	public Professor(final ContactInformation ci, final int id, final String dob,
			final String dept) {

		super(ci, id, dob);
		department = dept;
	}

	// when creating before adding to database
	public Professor(final ContactInformation ci, final String dob,
			final String dept) {

		super(ci, -1, dob);

		department = dept;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String dpt) {
		department = dpt;
	}
	
	@Override
	public String toString() {
		return "id: " + id;
	}
}
