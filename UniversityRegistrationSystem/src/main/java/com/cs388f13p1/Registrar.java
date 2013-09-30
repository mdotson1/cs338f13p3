package com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Registrar {
	
	public Registrar() {
		
	}
	
	public List<CourseOffering> getCourseCatalog(final Semester sem) {
		return CourseOfferingManager.getInstance().getCoursesInSemester(sem);
	}
	
	public List<Student> getRosterForCourse(final CourseOffering course) {
		return CourseOfferingManager.getInstance().getRoster(course);
	}
	
	// modify courseoffering
	public boolean assignProfessorToCourse(final CourseOffering course, 
			final Professor prof) {
		return CourseOfferingManager.getInstance().assignProfessorForCourse(
				course, prof);
	}
	
	public boolean removeProfessorFromCourse(final CourseOffering course) {
		return CourseOfferingManager.getInstance().removeProfessor(course);
	}
	
	private boolean allCoursesInSameSemester(
			final Iterator<CourseOffering> courses) {
		final Semester firstSemester;
		
		if (courses.hasNext()) {
			firstSemester = courses.next().getSemester();
		} else {
			return false;
		}
		while (courses.hasNext()) {
			if (!(courses.next().getSemester().equals(firstSemester))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Enrolls a student in the desired courses 
	 * @param s student to add courses to
	 * @param desiredCourses list of all courses a student wishes to enroll in
	 * @param alternatives list of alternative courses to enroll in if desired 
	 * 			is full
	 * @return true if registered, false if not
	 */
	public boolean registerForCourses(final Student s, 
			final List<CourseOffering> desiredCourses, 
			final List<CourseOffering> alternatives, final Date currentDate) {
		
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
	
	public boolean beforeDropDate(final Date currentDate, final Semester sem) {
		if (currentDate.compareTo(sem.dropDate()) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	public boolean addAfterRegistering(final Student s, 
			final CourseOffering course, final Date currentDate) {
		
		if (validDate(s, course.getSemester(), currentDate)) {
			return enrollStudentInCourse(s, course);
		} else {
			return false;
		}
	}
	*/
	
	/**
	 * Add a course to a student and added student to course
	 * @param s student to add course to
	 * @param course to add to student
	 * @return true if added both, false if not
	 */
	public boolean enrollStudentInCourse(final Student s, 
			final CourseOffering course) {
		
		final boolean addedCourseToStudent = 
				StudentManager.getInstance().enrollInCourse(s, course);
		
		if (addedCourseToStudent) {
			final boolean addedStudentToCourse = 
					CourseOfferingManager.getInstance().addStudent(s, course);
			
			if (addedStudentToCourse) {
				return true;
			} else {
				CourseOfferingManager.getInstance().dropStudent(s, course);
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean dropStudentFromCourse(final Student s, 
			final CourseOffering course, final Date currentDate) {
		
		if (validDate(s, course.getSemester(), currentDate)) {	
			
			final boolean droppedCourseFromStudent = 
					StudentManager.getInstance().dropCourse(s, course);
			
			if (droppedCourseFromStudent) {
				
				final boolean droppedStudentFromCourse = 
						CourseOfferingManager.getInstance().dropStudent(s, 
								course);
				
				if (droppedStudentFromCourse) {
					return true;
				} else {
					StudentManager.getInstance().enrollInCourse(s, course);
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Can only enroll or drop after registering and 1 week before classes
	 * @param sem course's semester
	 * @param currentDate
	 * @return true if the date is valid for enrolling or dropping
	 */
	private boolean validDate(Student student, Semester sem, Date currentDate) {
		if (student.hasRegistered()) {
			return beforeDropDate(currentDate, sem);
		} else {
			return false;
		}
	}
}
