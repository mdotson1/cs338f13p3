package controllers.services;

import java.sql.SQLException;
import java.util.Iterator;

import models.course.CourseOffering;
import models.course.Semester.Season;
import models.person.Student;

// registrar should pass values to services which look up the values
public class Registrar {
	
	private Registrar() { } // services cannot be instantiated
	
	public static Iterator<CourseOffering> getCourseCatalog(final Season s, final short year) throws SQLException {
		
		return CourseOfferingService.getCoursesInSemester(s, year);
	}
	
	public static Iterator<Student> getRosterForCourse(final int courseOfferingId) throws SQLException {
		
		return CourseOfferingService.getRoster(courseOfferingId);
	}

    /*
	public static boolean assignProfessorToCourse(final int courseOfferingId, 
			final int professorId) throws SQLException {
		
		boolean successful = ProfessorCourseService.assignProfessorForCourse(courseOfferingId, professorId);
		
		return successful;
	}
	
	public static boolean removeProfessorFromCourse(final int courseOfferingId) throws SQLException {
		return CourseOfferingService.removeProfessor(courseOfferingId);
	}
	*/

    /*
	private static boolean allCoursesInSameSemester(final int[] courseIdsToCheck) throws SQLException {
		
		return CourseOfferingService.allCoursesInSameSemester(courseIdsToCheck);
	}
	*/
	
	/*
	public boolean registerForCourses(final int studentId, 
			final Iterator<CourseOffering> desiredCourses, 
			final Iterator<CourseOffering> alternatives) {
		
		final ArrayList<CourseOffering> concatenated = 
				new ArrayList<CourseOffering>(desiredCourses);
		concatenated.addAll(alternatives);
		final Iterator<CourseOffering> desiredAndAlternatives = 
				concatenated.iterator();
		
		if (allCoursesInSameSemester(desiredAndAlternatives)) {
			if (! s.hasRegistered()) {
				// get first section's semesters, because they are guaranteed to
				// be identical at this point
				if (beforeDropDate(currentDate, 
						concatenated.get(0).getSemester())) {
					// this will do desired semesters first and alternatives
					// next, because they were concatenated in that order.
					while (desiredAndAlternatives.hasNext()) {
						enrollStudentInCourse(s, desiredAndAlternatives.next());
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	*/
	
	public static boolean enrollStudentInCourse(final int studentId, 
			final int courseOfferingId) throws SQLException {
		
		final boolean addedCourseToStudent = 
				StudentCourseService.enrollInCourse(studentId, courseOfferingId);
		
		if (addedCourseToStudent) {
			final boolean addedStudentToCourse = 
					CourseOfferingService.addStudent(studentId, courseOfferingId);
			
			if (addedStudentToCourse) {
				return true;
			} else {
				CourseOfferingService.dropStudent(studentId, courseOfferingId);
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean dropStudentFromCourse(final int studentId, 
			final int courseOfferingId) throws SQLException {
		
		final boolean droppedCourseFromStudent = 
				StudentCourseService.dropCourse(studentId, courseOfferingId);

		return droppedCourseFromStudent;
	}
}
