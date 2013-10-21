package com.cs388f13p2.model.services;

import java.sql.SQLException;

import com.cs388f13p2.database.dao.relationships.CoursesTeachingRepository;
import com.cs388f13p2.model.person.Professor;

public class ProfessorCourseService {

	private ProfessorCourseService() { } // impossible to instantiate a service

	// true = assigned prof, false = not assigned
	public static boolean assignProfessorForCourse(final int courseOfferingId,
			final int professorId) throws SQLException {

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
