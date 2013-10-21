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
			System.out.println(studentId);
			Iterator<CourseOffering> courses = CoursesTakingRepository.getInstance().getCoursesTakingByStudent(studentId);
			
			double currentBalance = student.getCurrentBalance();
			while (courses.hasNext()) {
				
				Course course = courses.next().getCourse();
				System.out.println(course.getDepartment());
				currentBalance =- CourseRepository.getInstance().findById(course.getDepartment(),
						course.getCourseNumber()).getCost();
				System.out.println(student);
			}
			
			StudentRepository.getInstance().updateBalance(studentId, currentBalance);
		}
	}

	// true if paid, false if not (possibly doesn't accept checks, for example
	public static boolean payBalance(final int studentId, final int paymentId)
			throws SQLException {
		
		Payment p = PaymentRepository.getInstance().findById(paymentId);
		
		StudentRepository.getInstance().updateBalance(studentId, p.getPaymentAmount());
		
		PaymentHistoryRepository.getInstance().add(studentId, paymentId);
		
		return true;
	}

	// true = enrolled in, false = not enrolled in
	public static boolean enrollInCourse(final int studentId,
			final int courseOfferingId) throws SQLException {
		
		if (CoursesTakingRepository.getInstance().
				findNumberOfCoursesTakenByStudent(studentId) == 4) {
			return false;
		} else {
			if (studentIsTakingCourse(studentId, courseOfferingId)) {
				return false;
			} else {
				CoursesTakingRepository.getInstance().add(studentId, courseOfferingId);
				return true;
			}
		}
	}

	// true = dropped, false = not dropped
	public static boolean dropCourse(final int studentId,
			final int courseOfferingId) throws SQLException {
		
		if (studentIsTakingCourse(studentId, courseOfferingId)) {
			CoursesTakingRepository.getInstance().delete(studentId, courseOfferingId);
			return true;
		} else {
			return false;
		}
	}

	// private class, so can use objects instead of keys
	private static boolean studentIsTakingCourse(final int studentId,
			final int courseOfferingId) throws SQLException {
		
		return CoursesTakingRepository.getInstance().contains(studentId, courseOfferingId);
	}
}
