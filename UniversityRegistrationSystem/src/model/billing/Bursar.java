package model.billing;

import model.person.Student;

public class Bursar {
	
	public void billStudent(Student s) {
		s.bill();
	}
	
	public void payBalance(Student s, Payment p) {
		s.payBalance(p);
	}
}
