package models.person;

public class Student extends UniversityPerson {

	private double currentBalance;

    // bean for play
    public Student() {
        super();
    }

	// when retrieving from database, has student
	public Student(final ContactInformation ci, final int id, final String dob,
			final double currentBalance) {
		
		super(ci, id, dob);
		
		this.currentBalance = currentBalance;
	}
	
	// when creating before adding to database
	public Student(final ContactInformation ci, final String dob,
			final double currentBalance) {
		
		super(ci, -1, dob);
		
		this.currentBalance = currentBalance;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double balance) {
		currentBalance = balance;
	}

	public boolean equals(Student s) {
		return this.getId() == s.getId();
	}

	@Override
	public String toString() {
		String retVal = "student: " + id +
                ((contactInformation != null) ? contactInformation.toString() : "")
                + " " + " current-balance: " + currentBalance;
        return retVal;
	}

}
