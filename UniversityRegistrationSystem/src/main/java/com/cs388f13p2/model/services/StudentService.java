package com.cs388f13p2.model.services;

import java.util.Iterator;

import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.person.Payment;
import com.cs388f13p2.model.person.Student;
import com.cs388f13p2.model.repositories.CourseOfferingRepository;
import com.cs388f13p2.model.repositories.StudentRepository;

public class StudentService {
	
	private StudentService() { } // impossible to instantiate a service

	public static void billStudents() {

		Iterator<Student> it = StudentRepository.getInstance().getAll();

		while (it.hasNext()) {
			Student student = it.next();
			Iterator<CourseOffering> coursesTaking = student.getCourses();

			double currentBalance = student.getCurrentBalance();

			while (coursesTaking.hasNext()) {
				currentBalance =+ coursesTaking.next().getCost();
			}

			student.setCurrentBalance(currentBalance);

			StudentRepository.getInstance().update(student.getId(), student);
		}
	}

	// true if paid, false if not (possibly doesn't accept checks, for example
	public static boolean payBalance(final int studentId, Payment p) {
		
		Student student = StudentRepository.getInstance().findById(studentId);

		student.getPaymentHistory().add(p);
		double currentBalance = student.getCurrentBalance() - p.getPaymentAmount();
		student.setCurrentBalance(currentBalance);

		StudentRepository.getInstance().update(student.getId(), student);
		
		return true;
	}

	public static boolean enrollInCourse(final int studentId,
			final int courseOfferingId) {
		
		Student student = StudentRepository.getInstance().findById(studentId);
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);

		if (student.getNumCourses() == 4) {
			return false;
		} else {
			if (studentIsTakingCourse(student, course)) {
				return false;
			} else {
				student.addCourse(course);
				StudentRepository.getInstance().update(student.getId(), student);
				return true;
			}
		}
	}

	// true = dropped, false = not dropped
	public static boolean dropCourse(final int studentId,
			final int courseOfferingId) {
		
		Student student = StudentRepository.getInstance().findById(studentId);
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		if (studentIsTakingCourse(student, course)) {
			student.removeCourse(course);
			StudentRepository.getInstance().update(student.getId(), student);
			return true;
		} else {
			return false;
		}
	}

	// private class, so can use objects instead of keys
	private static boolean studentIsTakingCourse(final Student stu,
			final CourseOffering course) {
		
		Iterator<CourseOffering> it = stu.getCourses();

		while (it.hasNext()) {
			if (it.next().equals(course)) {
				return true;
			}
		}
		return false;
	}
}
