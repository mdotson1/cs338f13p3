package com.cs388f13p1;

public class Bursar {
	
	// should be called only after drop date
	public void billStudents() {
		StudentService.billStudents();
	}
	
	public void payBalance(final int studentId, final int paymentId) {
		
		Student s = StudentRepository.getInstance().findById(studentId);
		Payment p = PaymentRepository.getInstance().findById(paymentId);
		
		StudentService.payBalance(s, p);
	}
}
