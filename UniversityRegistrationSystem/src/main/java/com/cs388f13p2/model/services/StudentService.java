package com.cs388f13p2.model.services;

import java.sql.SQLException;
import java.util.Iterator;

import com.cs388f13p2.database.dao.concrete.CourseRepository;
import com.cs388f13p2.database.dao.concrete.PaymentRepository;
import com.cs388f13p2.database.dao.concrete.StudentRepository;
import com.cs388f13p2.database.dao.relationships.CoursesTakingRepository;
import com.cs388f13p2.database.dao.relationships.PaymentHistoryRepository;
import com.cs388f13p2.model.course.Course;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.person.Payment;
import com.cs388f13p2.model.person.Student;

public class StudentService {
	
	private StudentService() { } // impossible to instantiate a service

	public static void billStudents() throws SQLException {

		Iterator<Student> students = StudentRepository.getInstance().getAll();
		
		while (students.hasNext()) {
			
			Student student = students.next();
			
			int studentId = student.getId();
			Iterator<CourseOffering> courses = CoursesTakingRepository.getInstance().getCoursesTakingByStudent(studentId);
			
			double currentBalance = student.getCurrentBalance();
			while (courses.hasNext()) {
				
				Course course = courses.next().getCourse();
				currentBalance =- CourseRepository.getInstance().findById(course.getDepartment(),
						course.getCourseNumber()).getCost();
			}
			
			StudentRepository.getInstance().updateBalance(studentId, currentBalance);
		}
	}

	// true if paid, false if not (possibly doesn't accept checks, for example
	public static boolean payBalance(final int studentId, final int paymentId)
			throws SQLException {
		
		Payment p = PaymentRepository.getInstance().findById(paymentId);
		
		Student s = StudentRepository.getInstance().findById(studentId);
		
		double newBalance = s.getCurrentBalance() + p.getPaymentAmount();
		StudentRepository.getInstance().updateBalance(studentId, newBalance);
		
		PaymentHistoryRepository.getInstance().add(studentId, paymentId);
		
		return true;
	}
}
