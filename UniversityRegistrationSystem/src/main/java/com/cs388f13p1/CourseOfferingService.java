package com.cs388f13p1;

import java.util.Iterator;

import com.cs388f13p1.CourseOffering.Season;

public class CourseOfferingService {
	
	private CourseOfferingService() { } // impossible to instantiate a service
	
	public static Iterator<CourseOffering> getCoursesInSemester(Season season, short year) {
		return CourseOfferingRepository.getInstance().findAllCoursesBySemester(season, year);
	}
	
	// true = added, false = not added
	public static boolean addStudent(final int studentId, final int courseOfferingId) {
		
		Student s = StudentRepository.getInstance().findById(studentId);
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		if (course.getNumberOfStudents() == 10) {
			return false;
		} else {
			course.addStudent(s);
			
			CourseOfferingRepository.getInstance().update(courseOfferingId, course);
			
			return true;
		}
	}
	
	public static boolean dropStudent(final int studentId, 
			final int courseOfferingId) {
		
		Student s = StudentRepository.getInstance().findById(studentId);
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		if (course.getNumberOfStudents() == 3) {
			return false;
		} else {
			course.removeStudent(s);
			
			CourseOfferingRepository.getInstance().update(courseOfferingId, course);
			
			return true;
		}
	}
	
	// true = assigned prof, false = not assigned
	public static boolean assignProfessorForCourse(final int courseOfferingId,
			final int professorId) {
		
		Professor prof = ProfessorRepository.getInstance().findById(professorId);
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		if (course.getProfessor() == null) {
			course.setProfessor(prof);
			
			CourseOfferingRepository.getInstance().update(courseOfferingId, course);
			
			return true;
		} else {
			return false;
		}
	}
	
	// true = removed prof, false = not removed
	public static boolean removeProfessor(final int courseOfferingId) {
		
		CourseOffering course = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		if (course.getProfessor() == null) {
			return false;
		} else {
			course.setProfessor(null);
			CourseOfferingRepository.getInstance().update(courseOfferingId, course);
			return true;
		}
	}
	
	public static Iterator<Student> getRoster(final int courseOfferingId) {
		
		CourseOffering co = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		return co.getRoster();
	}

	public static boolean allCoursesInSameSemester(Iterator<CourseOffering> coursesToCheck) {
		
		final Season season;
		final short year;
		CourseOffering co;
		
		if (coursesToCheck.hasNext()) {
			co = coursesToCheck.next();
			season = co.getSeason();
			year = co.getYear();
		} else {
			return true;
		}
		
		boolean inSameSemester = true;
		while (coursesToCheck.hasNext()) {
			
			co = coursesToCheck.next();
			
			if (!((co.getSeason() == season) && (co.getYear() == year))) {
				inSameSemester = false;
				break;
			}
		}
		
		return inSameSemester;
	}
}
