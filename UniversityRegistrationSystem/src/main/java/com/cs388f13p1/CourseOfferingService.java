package com.cs388f13p1;

import java.util.Iterator;

public class CourseOfferingService {
	
	public Iterator<CourseOffering> getCoursesInSemester(final Semester sem) {
		
		return CourseOfferingRepository.getInstance().getAllCoursesInSemester();
	}
	
	// true = added, false = not added
	public boolean addStudent(final Student s, final CourseOffering course) {
		
		if (course.getNumberOfStudents() == 10) {
			return false;
		} else {
			return course.addStudent(s);
		}
	}
	
	public boolean dropStudent(final Student student, 
			final CourseOffering course) {
		
		if (course.getNumberOfStudents() == 3) {
			return false;
		} else {
			return course.removeStudent(student);
		}
	}
	
	// true = assigned prof, false = not assigned
	public boolean assignProfessorForCourse(final CourseOffering course,
			final Professor prof) {
		
		if (course.getProfessor() == null) {
			course.setProfessor(prof);
			return true;
		} else {
			return false;
		}
	}
	
	// true = removed prof, false = not removed
	public boolean removeProfessor(final CourseOffering course) {
		if (course.getProfessor() == null) {
			return false;
		} else {
			course.setProfessor(null);
			CourseOfferingRepository.getInstance().update(course.getId(), course);
			return true;
		}
	}
	
	public Iterator<Student> getRoster(final CourseOffering course) {
		return CourseOfferingRepository.getInstance().findById(courseOfferingId).getRoster();
	}
}
