package com.cs388f13p2.view;

import java.util.Iterator;

import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;
import com.cs388f13p2.model.person.Address;
import com.cs388f13p2.model.person.ContactInformation;
import com.cs388f13p2.model.person.Payment;
import com.cs388f13p2.model.person.Professor;
import com.cs388f13p2.model.person.Student;
import com.cs388f13p2.model.repositories.CourseOfferingRepository;
import com.cs388f13p2.model.repositories.ProfessorRepository;
import com.cs388f13p2.model.repositories.StudentRepository;
import com.cs388f13p2.model.services.Bursar;
import com.cs388f13p2.model.services.Registrar;

public class Registration {
	
	private static void ASSIGN_PROF_TEST(
			final int professorId, final int courseOfferingId) {
		
		System.out.println("ASSIGN_PROF_TEST");
		
		Professor p = CourseOfferingRepository.getInstance().findById(courseOfferingId).getProfessor();
		
		System.out.println("before-adding-professor: " + p);
		Registrar.assignProfessorToCourse(courseOfferingId, professorId);
		
		p = CourseOfferingRepository.getInstance().findById(courseOfferingId).getProfessor();
		
		System.out.println("after-adding-professor: " + p);
		
		System.out.println("");
	}
	
	private static void REMOVE_PROF_TEST(final int courseOfferingId) {
		
		System.out.println("REMOVE_PROF_TEST");
		
		Professor p = CourseOfferingRepository.getInstance().findById(courseOfferingId).getProfessor();
		
		System.out.println("before removing prof: " + p);
		Registrar.removeProfessorFromCourse(courseOfferingId);
		
		p = CourseOfferingRepository.getInstance().findById(courseOfferingId).getProfessor();
		
		System.out.println("after removing prof: " + p);
		
		System.out.println("");
	}
	
	private static void ENROLL_STUDENT_TEST(
			final int studentId, final int courseOfferingId) {
		
		System.out.println("ENROLL_STUDENT_TEST");
		
		Iterator<CourseOffering> courses = StudentRepository.getInstance().findById(studentId).getCourses();
		Iterator<Student> students = CourseOfferingRepository.getInstance().findById(courseOfferingId).getRoster();
		
		System.out.println("before-adding-student: " + courses);
		System.out.println("before-adding-course: " + students);
		Registrar.enrollStudentInCourse(studentId, courseOfferingId);
		
		courses = StudentRepository.getInstance().findById(studentId).getCourses();
		students = CourseOfferingRepository.getInstance().findById(courseOfferingId).getRoster();
		
		System.out.println("added-course-to-student: " + courses);
		System.out.println("added-student-to-course: " + students);
	}
	
	public static void main(String[] args) {
		
		// Student s1
		Address homeAddr = new Address(1100, "N Dearborn St", 903, "Chicago", "IL", 60610);
		Address workAddr = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		ContactInformation ci1 = new ContactInformation(homeAddr, workAddr, "Michael", "Dotson", null, null, "920-284-6892");
		
		// add student to database
		Student s1 = new Student(ci1, 435435345, "05/10/1990");
		StudentRepository.getInstance().add(s1);
		
		// add CourseOffering to database
		CourseOffering co1 = new CourseOffering("CS", (short) 170, null, 4000, 23423, Season.FALL, (short) 2013, (short) 1, "Introduction to Object Oriented Programming");
		CourseOfferingRepository.getInstance().add(co1);
		
		// Professor p1
		Address homeAddr2 = new Address(1900, "N State St", 0, "Chicago", "IL", 60610);
		Address workAddr2 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		ContactInformation ci2 = new ContactInformation(homeAddr2, workAddr2, "Konstantin", "Laufer", null, null, "666-777-8888");
		
		// add professor to database
		Professor p1 = new Professor(ci2, 888883, "04/15/1970", "CS");
		ProfessorRepository.getInstance().add(p1);
		
		// Professor p1
		Address homeAddr3 = new Address(1900, "N Clark St", 0, "Chicago", "IL", 60610);
		Address workAddr3 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		ContactInformation ci3 = new ContactInformation(homeAddr3, workAddr3, "Mark", "Albert", null, null, "555-666-7777");
		
		// add professor to database
		Professor p2 = new Professor(ci3, 43543543, "08/19/1980", "CS");
		ProfessorRepository.getInstance().add(p2);
		
		// ******************************* //
		// *********** TESTING *********** //
		// ******************************* //
		
		// assign first professor to course
		ASSIGN_PROF_TEST(p1.getId(), co1.getCourseOfferingId());
		
		// test assigning another professor to same course
		ASSIGN_PROF_TEST(p2.getId(), co1.getCourseOfferingId());
		
		// test removing professor from that course
		REMOVE_PROF_TEST(co1.getCourseOfferingId());
		
		ENROLL_STUDENT_TEST(s1.getId(), co1.getCourseOfferingId());
		
		// test getting roster
		Registrar.getRosterForCourse(co1.getCourseOfferingId());
		
		// test billing students for courses
		System.out.println("before-billing-student: " + s1.getCurrentBalance());
		Bursar.billStudents();
		System.out.println("after-billing-student: " + s1.getCurrentBalance());
		
		// test paying for courses
		System.out.println("before-paying: " + s1.getCurrentBalance());
		Bursar.payBalance(s1.getId(), new Payment("check", 2000));
		System.out.println("after-paying: " + s1.getCurrentBalance());
		
		// payment history
		System.out.println("payment-history: " + s1.getPaymentHistory());
		
		// test paying again for courses
		System.out.println("before-paying: " + s1.getCurrentBalance());
		Bursar.payBalance(s1.getId(), new Payment("check", 2000));
		System.out.println("after-paying: " + s1.getCurrentBalance());
				
		// payment history
		System.out.println("payment-history: " + s1.getPaymentHistory());
		
		// test dropping student from course
		System.out.println("before-dropping-student: " + s1.getCourses());
		System.out.println("before-dropping-course: " + co1.getRoster());
		//registrar.dropStudentFromCourse(s1, co1);
		System.out.println("dropped-course-from-student: " + s1.getCourses());
		System.out.println("dropped-student-from-course: " + co1.getRoster());
		
	}

}
