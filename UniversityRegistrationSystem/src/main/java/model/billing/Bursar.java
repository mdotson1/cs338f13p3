package model.billing;

import model.person.Student;
import model.person.StudentManager;

public class Bursar {
	
	// should be called only after drop date
	public void billStudents() {
		StudentManager.getInstance().billStudents();
	}
	
	public void payBalance(final Student s, final Payment p) {
		StudentManager.getInstance().payBalance(s, p);
	}
}
