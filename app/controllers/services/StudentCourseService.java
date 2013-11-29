package controllers.services;

import java.sql.SQLException;
import java.util.Iterator;

import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import models.person.Student;

public class StudentCourseService {

    public static Iterator<Student> studentsTakingCourse(final Semester.Season season,
                                                           final short year,
                                                           final String department,
                                                           final String courseNum,
                                                           final String sectionNum)
            throws SQLException {

        final CourseOffering co = CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum), Short.parseShort(sectionNum));

        return CoursesTakingRepository.getInstance().findStudentsTakingCourse(
                co.getCourseOfferingId());
    }

    /*
	// true = enrolled in, false = not enrolled in
	public static boolean enrollInCourse(final int studentId,
			final int courseOfferingId) throws SQLException {

		if (CoursesTakingRepository.getInstance().
				findNumberOfCoursesTakingByStudent(studentId) == 4) {
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
	*/

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
