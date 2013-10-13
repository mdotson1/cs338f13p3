package com.cs388f13p2.model.services;

import java.sql.SQLException;

import com.cs388f13p2.database.dao.relationships.CoursesTeachingRepository;
import com.cs388f13p2.model.person.Professor;

public class ProfessorService {

	private ProfessorService() { } // impossible to instantiate a service
	
	public static boolean assignCourseForProfessor(int courseOfferingId,
			int professorId) throws SQLException {
		
		Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
		
		if (p == null) {
			CoursesTeachingRepository.getInstance().add(professorId, courseOfferingId);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean dropCourseFromProfessor(int professorId,
			int courseOfferingId) throws SQLException {
		
		return CoursesTeachingRepository.getInstance().delete(professorId, courseOfferingId);
	}
	
}
