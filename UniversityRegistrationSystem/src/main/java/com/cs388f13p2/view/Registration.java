package com.cs388f13p2.view;

import java.sql.SQLException;
import java.util.Iterator;

import com.cs388f13p2.database.dao.concrete.CourseOfferingRepository;
import com.cs388f13p2.database.dao.concrete.CourseRepository;
import com.cs388f13p2.database.dao.concrete.PaymentRepository;
import com.cs388f13p2.database.dao.concrete.ProfessorRepository;
import com.cs388f13p2.database.dao.concrete.StudentRepository;
import com.cs388f13p2.database.dao.relationships.CoursesTakingRepository;
import com.cs388f13p2.database.dao.relationships.CoursesTeachingRepository;
import com.cs388f13p2.database.dao.relationships.PaymentHistoryRepository;
import com.cs388f13p2.database.repository.Pair;
import com.cs388f13p2.model.course.Course;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;
import com.cs388f13p2.model.person.ContactInformation;
import com.cs388f13p2.model.person.Payment;
import com.cs388f13p2.model.person.Professor;
import com.cs388f13p2.model.person.Student;
import com.cs388f13p2.model.services.Bursar;
import com.cs388f13p2.model.services.Registrar;

public class Registration {

	private static void ASSIGN_PROF_TEST(
			final int professorId, final int courseOfferingId) {

		System.out.println("ASSIGN_PROF_TEST");

		try {
			Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
			System.out.println("before-adding-professor: " + p);

			Registrar.assignProfessorToCourse(courseOfferingId, professorId);

			p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
			System.out.println("after-adding-professor: " + p);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void REMOVE_PROF_TEST(final int courseOfferingId) {

		System.out.println("REMOVE_PROF_TEST");

		try {
			Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
			System.out.println("before removing prof: " + p);

			Registrar.removeProfessorFromCourse(courseOfferingId);

			p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
			System.out.println("after removing prof: " + p);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void ENROLL_STUDENT_TEST(
			final int studentId, final int courseOfferingId) {

		System.out.println("ENROLL_STUDENT_TEST");

		try {

			Pair<Student,CourseOffering> pair = CoursesTakingRepository.getInstance().findById(studentId, courseOfferingId);
			System.out.println("before-enrolling-student: " + pair.first());
			System.out.println("before-enrolling-course: " + pair.second());

			Registrar.enrollStudentInCourse(studentId, courseOfferingId);

			pair = CoursesTakingRepository.getInstance().findById(studentId, courseOfferingId);
			System.out.println("after-enrolling-student: " + pair.first());
			System.out.println("after-enrolling-course: " + pair.second());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void GET_ROSTER_TEST(final int courseOfferingId) {

		System.out.println("GET_ROSTER_TEST");

		try {

			Iterator<Student> it = Registrar.getRosterForCourse(courseOfferingId);

			int index = 0;
			while (it.hasNext()) {
				System.out.println("student-" + index + ": " + it.next());
				index++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void BILL_STUDENTS_FOR_COURSES_TEST() {

		System.out.println("BILL_STUDENTS_FOR_COURSES_TEST");

		try {

			Iterator<Student> it = StudentRepository.getInstance().getAll();

			while (it.hasNext()) {
				Student that = it.next();
				System.out.println("before-billing-student" + that.getId() + ": " + that.getCurrentBalance());
			}

			Bursar.billStudents();

			it = StudentRepository.getInstance().getAll();

			while (it.hasNext()) {
				Student that = it.next();
				System.out.println("after-billing-student" + that.getId() + ": " + that.getCurrentBalance());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void PAY_FOR_COURSES_TEST(final int studentId) {

		System.out.println("PAY_FOR_COURSES_TEST");

		try {
			Student s1 = StudentRepository.getInstance().findById(studentId);
			System.out.println("before-paying: " + s1.getCurrentBalance());

			Payment p = new Payment("check", 2000);
			int id = PaymentRepository.getInstance().add(p);
			p.setPaymentId(id);

			p = PaymentRepository.getInstance().findById(p.getPaymentId());
			Bursar.payBalance(s1.getId(), p.getPaymentId());

			s1 = StudentRepository.getInstance().findById(studentId);
			System.out.println("after-paying: " + s1.getCurrentBalance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void GET_PAYMENT_HISTORY_TEST(final int studentId) {

		System.out.println("GET_PAYMENT_HISTORY_TEST");

		try {
			Iterator<Payment> it = PaymentHistoryRepository.getInstance().findAllPaymentsByStudent(studentId);

			int index = 0;
			while (it.hasNext()) {
				System.out.println("payment-" + index + ": " + it.next());
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	private static void DROP_STUDENT_FROM_COURSE_TEST(final int studentId, final int courseOfferingId) {

		System.out.println("DROP_STUDENT_FROM_COURSE_TEST");

		try {
			Pair<Student,CourseOffering> pair = CoursesTakingRepository.getInstance().findById(studentId, courseOfferingId);
			System.out.println("before-dropping-student: " + pair.first());
			System.out.println("before-dropping-course: " + pair.second());

			Registrar.dropStudentFromCourse(studentId, courseOfferingId);

			pair = CoursesTakingRepository.getInstance().findById(studentId, courseOfferingId);
			System.out.println("after-dropping-student: " + pair.first());
			System.out.println("after-dropping-course: " + pair.second());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	public static void main(String[] args) {

		// Student s1
		ContactInformation ci1 = new ContactInformation("1100 N Dearborn St. Apt #903 Chicago, IL 60610",
				"110 E Pearson St. #520 Chicago, IL 60610", "Michael", "Dotson", null, null, "920-284-6892");

		// add student to database
		Student s1 = new Student(ci1, "05/10/1990", 0);
		try {
			int id = StudentRepository.getInstance().add(s1);
			s1.setId(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// add CourseOffering to database
		Course c1 = new Course("CS", (short) 170, 4000, "");
		CourseOffering co1 = new CourseOffering(433, c1, Season.FALL, (short) 2013, (short) 1);
		try {
			CourseRepository.getInstance().add(c1);
		} catch (SQLException e1) {
			if (e1.getErrorCode() == 1022 || e1.getErrorCode() == 1062) {
				System.out.println("Duplicate key: " + c1.getDepartment() + " " + c1.getCourseNumber());
			}
		}
		try {
			int id = CourseOfferingRepository.getInstance().add(co1);
			co1.setCourseOfferingId(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Professor p1
		ContactInformation ci2 = new ContactInformation("dont care", "dont care", "Konstantin", "Laufer", null, null, "666-777-8888");
		// add professor to database
		Professor p1 = new Professor(ci2, "04/15/1970", "CS");
		try {
			int id = ProfessorRepository.getInstance().add(p1);
			p1.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Professor p1
		ContactInformation ci3 = new ContactInformation("dont care", "dont care", "Mark", "Albert", null, null, "555-666-7777");
		// add professor to database
		Professor p2 = new Professor(ci3, "08/19/1980", "CS");
		try {
			int id = ProfessorRepository.getInstance().add(p2);
			p2.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ******************************* //
		// *********** TESTING *********** //
		// ******************************* //

		// assign first professor to course
		ASSIGN_PROF_TEST(p1.getId(), co1.getCourseOfferingId());

		// test assigning another professor to same course
		ASSIGN_PROF_TEST(p2.getId(), co1.getCourseOfferingId());

		// test removing professor from that course
		REMOVE_PROF_TEST(co1.getCourseOfferingId());

		// test enrolling a student in a course
		ENROLL_STUDENT_TEST(s1.getId(), co1.getCourseOfferingId());

		// test getting roster
		GET_ROSTER_TEST(co1.getCourseOfferingId());

		// test billing students for courses
		BILL_STUDENTS_FOR_COURSES_TEST();

		// test paying for courses
		PAY_FOR_COURSES_TEST(s1.getId());

		// payment history test
		GET_PAYMENT_HISTORY_TEST(s1.getId());

		// test paying again for courses
		PAY_FOR_COURSES_TEST(s1.getId());

		// payment history test
		GET_PAYMENT_HISTORY_TEST(s1.getId());

		// test dropping student from course
		DROP_STUDENT_FROM_COURSE_TEST(s1.getId(), co1.getCourseOfferingId());
	}

}
