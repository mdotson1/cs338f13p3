package com.cs388f13p1.model.services;

import com.cs388f13p1.model.person.Payment;

public class Bursar {
	
	private Bursar() { } // cannot instantiate services
	
	public static void billStudents() {
		StudentService.billStudents();
	}
	
	public static void payBalance(final int studentId, Payment p) {
		
		StudentService.payBalance(studentId, p);
	}
}
