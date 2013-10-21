package com.cs388f13p2.model.services;

import java.sql.SQLException;

import com.cs388f13p2.database.dao.relationships.CoursesTakingRepository;

public class StudentCourseService {
	// true = enrolled in, false = not enrolled in
	public static boolean enrollInCourse(final int studentId,
			final int courseOfferingId) throws SQLException {

		if (CoursesTakingRepository.getInstance().
				findNumberOfCoursesTakenByStudent(studentId) == 4) {
			return false;
		} else {
			if (studentIsTakingCourse(studentId, courseOfferingId)) {
				return false;
			} else {
				CoursesTakingRepository.getInstance().add(studentId, courseOfferingId);
				return true;
			}
		}
	}

	// true = dropped, false = not dropped
	public static boolean dropCourse(final int studentId,
			final int courseOfferingId) throws SQLException {

		if (studentIsTakingCourse(studentId, courseOfferingId)) {
			CoursesTakingRepository.getInstance().delete(studentId, courseOfferingId);
			return true;
		} else {
			return false;
		}
	}

	// private class, so can use objects instead of keys
	private static boolean studentIsTakingCourse(final int studentId,
			final int courseOfferingId) throws SQLException {

		return CoursesTakingRepository.getInstance().contains(studentId, courseOfferingId);
	}
}
