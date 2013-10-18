package com.cs388f13p2.model.services;

import java.sql.SQLException;

import com.cs388f13p2.database.dao.relationships.CoursesTeachingRepository;
import com.cs388f13p2.model.person.Professor;

public class ProfessorService {

	private ProfessorService() { } // impossible to instantiate a service
	
	public static boolean assignCourseForProfessor(int courseOfferingId,
			int professorId) throws SQLException {
		
		System.out.println("p1d1: " + courseOfferingId);
		Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
		
		if (p == null) {
			System.out.println("p1d2: " + courseOfferingId);
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
