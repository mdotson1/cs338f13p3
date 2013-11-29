package controllers.services;

import java.sql.SQLException;

public class Bursar {
	
	private Bursar() { } // cannot instantiate services

    /*
	public static void billStudents() throws SQLException {
		StudentService.billStudents();
	}
	*/
	
	public static void payBalance(final int studentId, final int paymentId) 
			throws SQLException {
		StudentService.payBalance(studentId, paymentId);
	}
}
