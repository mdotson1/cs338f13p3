package com.cs388f13p2.model.services;

import java.sql.SQLException;
import java.util.Iterator;

import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;
import com.cs388f13p2.model.person.Student;

// registrar should pass values to services which look up the values
public class Registrar {
	
	private Registrar() { } // services cannot be instantiated
	
	public static Iterator<CourseOffering> getCourseCatalog(final String s, final short year) throws SQLException {
		
		return CourseOfferingService.getCoursesInSemester(s, year);
	}
	
	public static Iterator<Student> getRosterForCourse(final int courseOfferingId) throws SQLException {
		
		return CourseOfferingService.getRoster(courseOfferingId);
	}
	
	public static boolean assignProfessorToCourse(final int courseOfferingId, 
			final int professorId) throws SQLException {
		
		System.out.println("p1c: " + courseOfferingId);
		boolean successful = ProfessorService.assignCourseForProfessor(courseOfferingId, professorId);
		if (successful) {
			successful = CourseOfferingService.assignProfessorForCourse(courseOfferingId, professorId);
		} else {
			return false;
		}
		if (successful) {
			return true;
		} else {
			// remove course from professor, since it didn't work.
			return ProfessorService.dropCourseFromProfessor(professorId, courseOfferingId);
		}
	}
	
	public static boolean removeProfessorFromCourse(final int courseOfferingId) throws SQLException {
		return CourseOfferingService.removeProfessor(courseOfferingId);
	}
	
	private static boolean allCoursesInSameSemester(final int[] courseIdsToCheck) throws SQLException {
		
		return CourseOfferingService.allCoursesInSameSemester(courseIdsToCheck);
	}
	
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
				// get first course's semester, because they are guaranteed to
				// be identical at this point
				if (beforeDropDate(currentDate, 
						concatenated.get(0).getSemester())) {
					// this will do desired courses first and alternatives
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
				StudentService.enrollInCourse(studentId, courseOfferingId);
		
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
				StudentService.dropCourse(studentId, courseOfferingId);

		if (droppedCourseFromStudent) {
			final boolean droppedStudentFromCourse = 
					CourseOfferingService.dropStudent(studentId, 
							courseOfferingId);

			if (droppedStudentFromCourse) {
				return true;
			} else {
				StudentService.enrollInCourse(studentId, courseOfferingId);
				return false;
			}
		} else {
			return false;
		}
	}
}
