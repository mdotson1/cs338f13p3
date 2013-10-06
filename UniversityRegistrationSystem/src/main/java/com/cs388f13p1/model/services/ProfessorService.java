package com.cs388f13p1.model.services;

import java.util.Iterator;

import com.cs388f13p1.model.course.CourseOffering;
import com.cs388f13p1.model.person.Professor;
import com.cs388f13p1.model.repositories.CourseOfferingRepository;
import com.cs388f13p1.model.repositories.ProfessorRepository;

public class ProfessorService {

	private ProfessorService() { } // impossible to instantiate a service
	
	public static boolean assignCourseForProfessor(int courseOfferingId,
			int professorId) {
		
		Professor p = ProfessorRepository.getInstance().findById(professorId);
		CourseOffering c = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		Iterator<CourseOffering> coursesTeaching = p.getCoursesTeaching();
		
		boolean alreadyTeachingCourse = false;
		while (coursesTeaching.hasNext()) {
			if (coursesTeaching.next().getCourseOfferingId() == courseOfferingId) {
				alreadyTeachingCourse = true;
				break;
			}
		}
		if (!alreadyTeachingCourse) {
			p.addCourseTeaching(c);
		}
	
		ProfessorRepository.getInstance().update(professorId, p);
		
		return !alreadyTeachingCourse;
	}
	
	public static boolean dropCourseFromProfessor(int professorId,
			int courseOfferingId) {
		
		Professor p = ProfessorRepository.getInstance().findById(professorId);
		CourseOffering c = CourseOfferingRepository.getInstance().findById(courseOfferingId);
		
		boolean successful = p.removeCourseTeaching(c);
		
		ProfessorRepository.getInstance().update(professorId, p);
		
		return successful;
	}
	
}
