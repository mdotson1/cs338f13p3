package com.cs388f13p1;

import com.cs388f13p1.Semester.Season;

public class Registration {
	
	private static void ASSIGN_PROF_TEST(final Registrar registrar, 
			final Professor p, final CourseOffering co) {
		
		System.out.println("ASSIGN_PROF_TEST");
		
		System.out.println("before-adding-professor: " + co.getProfessor());
		registrar.assignProfessorToCourse(co, p);
		System.out.println("after-adding-professor: " + co.getProfessor());
		
		System.out.println("");
	}
	
	private static void REMOVE_PROF_TEST(final Registrar registrar, 
			final CourseOffering co) {
		
		System.out.println("REMOVE_PROF_TEST");
		
		// test removing professor from course
		System.out.println("before removing prof: " + co.getProfessor());
		registrar.removeProfessorFromCourse(co);
		System.out.println("after removing prof: " + co.getProfessor());
		
		System.out.println("");
	}
	
	private static void ENROLL_STUDENT_TEST(final Registrar registrar,
			final Student s, final CourseOffering co) {
		
		System.out.println("ENROLL_STUDENT_TEST");
		
		// test enrolling student
		System.out.println("before-adding-student: " + s.getCourses());
		System.out.println("before-adding-course: " + co.getRoster());
		registrar.enrollStudentInCourse(s, co);
		System.out.println("added-course-to-student: " + s.getCourses());
		System.out.println("added-student-to-course: " + co.getRoster());
	}
	
	public static void main(String[] args) {
		
		Registrar registrar = new Registrar();
		Bursar bursar = new Bursar();
		
		// Student s1
		Address homeAddr = new Address(1100, "N Dearborn St", 903, "Chicago", "IL", 60610);
		Address workAddr = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones = new PhoneNumbers(null, "920-284-6892", "920-284-6892");
		Date dob = new Date((byte) 5, (byte) 10, (short) 1990);
		
		// add student to database or not if already exists
		Student s1 = StudentService.getInstance().add(homeAddr, workAddr, phones, "Michael", "Dotson", dob, 987654);
		
		// CourseOffering co1
		Date start = new Date((byte) 8, (byte) 26, (short) 2013);
		Date end = new Date((byte) 12, (byte) 15, (short) 2013);
		Period length = new Period(start, end);
		Semester sem = new Semester(Season.FALL, length);
		
		// add CourseOffering to database
		CourseOffering co1 = CourseOfferingService.getInstance().add("CS", (short) 170, null, 4000, sem, (short) 1, "Introduction to Object Oriented Programming");
		
		// Professor p1
		Address homeAddr2 = new Address(1900, "N State St", 0, "Chicago", "IL", 60610);
		Address workAddr2 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones2 = new PhoneNumbers(null, "262-363-2225", "262-363-2225");
		Date dob2 = new Date((byte) 10, (byte) 31, (short) 1965);
		
		// add professor to database
		Professor p1 = ProfessorService.getInstance().add(homeAddr2, workAddr2, phones2, "Konstantin", "Laufer", dob2, 888883, "CS");
		
		// Professor p1
		Address homeAddr3 = new Address(1900, "N Clark St", 0, "Chicago", "IL", 60610);
		Address workAddr3 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones3 = new PhoneNumbers(null, "555-555-5555", "555-555-5555");
		Date dob3 = new Date((byte) 6, (byte) 11, (short) 1975);
		
		// add professor to database
		Professor p2 = ProfessorService.getInstance().add(homeAddr3, workAddr3, phones3, "Mark", "Albert", dob3, 43543543, "CS");
		
		// ******************************* //
		// *********** TESTING *********** //
		// ******************************* //
		
		// assign first professor to course
		ASSIGN_PROF_TEST(registrar, p1, co1);
		
		// test assigning another professor to same course
		ASSIGN_PROF_TEST(registrar, p2, co1);
		
		// test removing professor from that course
		REMOVE_PROF_TEST(registrar, co1);
		
		ENROLL_STUDENT_TEST(registrar, s1, co1);
		
		// test getting roster
		registrar.getRosterForCourse(co1);
		
		// test billing students for courses
		System.out.println("before-billing-student: " + s1.getCurrentBalance());
		bursar.billStudents();
		System.out.println("after-billing-student: " + s1.getCurrentBalance());
		
		// test paying for courses
		System.out.println("before-paying: " + s1.getCurrentBalance());
		bursar.payBalance(s1, new Payment("check", 2000));
		System.out.println("after-paying: " + s1.getCurrentBalance());
		
		// payment history
		System.out.println("payment-history: " + s1.getPaymentHistory());
		
		// test paying again for courses
		System.out.println("before-paying: " + s1.getCurrentBalance());
		bursar.payBalance(s1, new Payment("check", 2000));
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
