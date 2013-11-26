package models.person;

public class UniversityPerson extends Person {

	protected int id;

    // bean for play
    public UniversityPerson() {
        super();
    }

	public UniversityPerson(final ContactInformation ci, final int id, 
			final String dob) {
		
		super(ci, dob);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
