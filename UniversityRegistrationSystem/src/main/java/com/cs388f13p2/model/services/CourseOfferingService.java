package com.cs388f13p2.model.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cs388f13p2.database.dao.concrete.CourseOfferingRepository;
import com.cs388f13p2.database.dao.relationships.CoursesTakingRepository;
import com.cs388f13p2.database.dao.relationships.CoursesTeachingRepository;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;
import com.cs388f13p2.model.person.Professor;
import com.cs388f13p2.model.person.Student;

public class CourseOfferingService {

	private CourseOfferingService() { } // impossible to instantiate a service

	public static Iterator<CourseOffering> getCoursesInSemester(String season, short year) throws SQLException {
		return CourseOfferingRepository.getInstance().findAllCoursesBySemester(season, year);
	}

	// true = added, false = not added
	public static boolean addStudent(final int studentId, final int courseOfferingId) throws SQLException {

		if (CoursesTakingRepository.getInstance().findNumberOfStudentsTakingCourse(courseOfferingId) >= 10) {
			return false;
		} else {
			CoursesTakingRepository.getInstance().add(studentId, courseOfferingId);
			return true;
		}
	}

	public static boolean dropStudent(final int studentId, final int courseOfferingId) throws SQLException {

		if (CoursesTakingRepository.getInstance().findNumberOfStudentsTakingCourse(courseOfferingId) <= 3) {
			return false;
		} else {
			CoursesTakingRepository.getInstance().delete(studentId, courseOfferingId);
			return true;
		}
	}

	// true = assigned prof, false = not assigned
	public static boolean assignProfessorForCourse(final int courseOfferingId,
			final int professorId) throws SQLException {

		if (CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId) == null) {

			CoursesTeachingRepository.getInstance().add(professorId, courseOfferingId);
			return true;
		} else {
			return false;
		}
	}

	// true = removed prof, false = not removed
	public static boolean removeProfessor(final int courseOfferingId) throws SQLException {

		Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
		if (p == null) {
			return false;
		} else {
			CoursesTeachingRepository.getInstance().delete(p.getId(), courseOfferingId);
			return true;
		}
	}

	public static Iterator<Student> getRoster(final int courseOfferingId) throws SQLException {

		return CoursesTakingRepository.getInstance().getStudentsTakingCourse(courseOfferingId);
	}

	public static boolean allCoursesInSameSemester(int[] courseIdsToCheck) throws SQLException {

		final Season season;
		final short year;
		CourseOffering co;
		
		List<CourseOffering> courses = new ArrayList<CourseOffering>();
		
		for (int i = 0; i < courseIdsToCheck.length; i++) {
			courses.add(CourseOfferingRepository.getInstance().findById(courseIdsToCheck[i]));
		}
		
		Iterator<CourseOffering> coursesToCheck = courses.iterator();

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
