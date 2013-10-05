package com.cs388f13p1;

import java.util.Iterator;

public class StudentService {

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

	public static void payBalance(final Student student, final Payment payment) {

		student.getPaymentHistory().add(payment);
		double currentBalance = student.getCurrentBalance() - payment.getPaymentAmount();
		student.setCurrentBalance(currentBalance);

		StudentRepository.getInstance().update(student.getId(), student);
	}

	public static boolean enrollInCourse(final Student student,
			final CourseOffering course) {

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
	public static boolean dropCourse(final Student student,
			final CourseOffering course) {
		
		if (studentIsTakingCourse(student, course)) {
			student.removeCourse(course);
			StudentRepository.getInstance().update(student.getId(), student);
			return true;
		} else {
			return false;
		}
	}

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
