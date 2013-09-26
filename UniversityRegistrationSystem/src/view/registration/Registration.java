package view.registration;

import model.billing.Bursar;
import model.billing.Payment;
import model.course.CourseManager;
import model.course.CourseOffering;
import model.course.CourseOfferingManager;
import model.person.Address;
import model.person.PhoneNumbers;
import model.person.Professor;
import model.person.ProfessorManager;
import model.person.Student;
import model.person.StudentManager;
import model.registration.CourseCatalogManager;
import model.registration.Registrar;
import model.time.Date;
import model.time.Period;
import model.time.Semester;
import model.time.Semester.Season;

public class Registration {
	
	public static void main(String[] args) {
		
		Registrar registrar = new Registrar();
		Bursar bursar = new Bursar();
		
		// Student s1
		Address homeAddr = new Address(1100, "N Dearborn St", 903, "Chicago", "IL", 60610);
		Address workAddr = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones = new PhoneNumbers(null, "920-284-6892", "920-284-6892");
		Date dob = new Date((byte) 5, (byte) 10, (short) 1990);
		Student s1 = new Student(homeAddr, workAddr, phones, "Michael", "Dotson", 9999999999L, dob);
		
		// CourseOffering co1
		CourseOffering co1 = new CourseOffering("CS", (short) 170, null, 4000, 
				new Semester(Season.FALL, new Period(
						new Date((byte) 8, (byte) 26, (short) 2013), new Date((byte) 12, (byte) 15, (short) 2013))), 
						(short) 1);
		
		System.out.println("before adding course: " + registrar.getCourseCatalog());
		registrar.addCourseToCatalog(co1);
		System.out.println("after adding course: " + registrar.getCourseCatalog());
		
		// Professor p1
		Address homeAddr2 = new Address(1900, "N State St", 0, "Chicago", "IL", 60610);
		Address workAddr2 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones2 = new PhoneNumbers(null, "262-363-2225", "262-363-2225");
		Date dob2 = new Date((byte) 10, (byte) 31, (short) 1965);
		Professor p1 = new Professor(homeAddr2, workAddr2, phones2, "Konstantin", "Laufer", 8888888888L, dob2, "CS");
		
		// Professor p1
		Address homeAddr3 = new Address(1900, "N Clark St", 0, "Chicago", "IL", 60610);
		Address workAddr3 = new Address(110, "E Pearson St", 520, "Chicago", "IL", 60610);
		PhoneNumbers phones3 = new PhoneNumbers(null, "555-555-5555", "555-555-5555");
		Date dob3 = new Date((byte) 6, (byte) 11, (short) 1975);
		Professor p2 = new Professor(homeAddr3, workAddr3, phones3, "Mark", "Albert", 7777777777L, dob3, "CS");
		
		// test assigning professor
		System.out.println("before-adding-professor: " + co1.getProfessor());
		registrar.assignProfessorToCourse(co1, p1);
		System.out.println("after-adding-professor: " + co1.getProfessor());
		System.out.println(co1.toString());
		
		// test assigning another professor to same course
		System.out.println("before adding another prof: " + co1.getProfessor());
		registrar.assignProfessorToCourse(co1, p2);
		System.out.println("after adding another prof: " + co1.getProfessor());
		
		// test removing professor from course
		System.out.println("before removing prof: " + co1.getProfessor());
		registrar.removeProfessorFromCourse(co1);
		System.out.println("after removing prof: " + co1.getProfessor());
		
		// test enrolling student
		System.out.println("before-adding-student: " + s1.getCourses());
		System.out.println("before-adding-course: " + co1.getRoster());
		//registrar.addAfterRegistering(s1, co1, );
		System.out.println("added-course-to-student: " + s1.getCourses());
		System.out.println("added-student-to-course: " + co1.getRoster());
		
		// test getting roster
		registrar.getRosterForCourse(co1);
		
		// test billing student for courses
		System.out.println("before-billing-student: " + s1.getCurrentBalance());
		bursar.billStudent(s1);
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
