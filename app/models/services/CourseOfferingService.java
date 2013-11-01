package models.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.course.CourseOffering;
import models.course.CourseOffering.Season;
import models.person.Professor;
import models.person.Student;

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
