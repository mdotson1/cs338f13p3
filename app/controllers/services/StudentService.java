package controllers.services;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.course.Course;
import models.course.CourseOffering;
import models.person.ContactInformation;
import models.person.Payment;
import models.person.Student;

public class StudentService {
	
	private StudentService() { } // impossible to instantiate a service

    public static void createStudent(Map<String,String> data)
            throws SQLException {

        final ContactInformation ci = new ContactInformation(
                data.get("Home Address"), data.get("Work Address"),
                data.get("First Name"), data.get("Last Name"),
                data.get("Work Phone"), data.get("Home Phone"),
                data.get("Cell Phone"));
        Student s = new Student(ci, data.get("Date of Birth"), 0);

        StudentRepository.getInstance().add(s);
    }

	public static void billStudents() throws SQLException {

		Iterator<Student> students = StudentRepository.getInstance().getAll();
		
		while (students.hasNext()) {
			
			Student student = students.next();
			
			int studentId = student.getId();
			Iterator<CourseOffering> courses = CoursesTakingRepository.
                    getInstance().getCoursesTakenByStudent(studentId);
			
			double currentBalance = student.getCurrentBalance();
			while (courses.hasNext()) {
				
				Course course = courses.next().getCourse();
				currentBalance =- CourseRepository.getInstance().findById(
                        course.getDepartment(), course.getCourseNumber()).
                        getCost();
			}
			
			StudentRepository.getInstance().updateBalance(studentId,
                    currentBalance);
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
