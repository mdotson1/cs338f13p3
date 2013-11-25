package controllers.services;

import java.sql.SQLException;
import java.util.Iterator;

import models.course.CourseOffering;
import models.course.Semester.Season;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;

public class ProfessorCourseService {

	private ProfessorCourseService() { } // impossible to instantiate a service

    /*
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
	*/
	public static Iterator<Professor> professorsTeachingCourse(final Season season,
                                                               final short year,
                                                               final String department,
                                                               final String courseNum,
                                                               final String sectionNum)
            throws SQLException {

        final CourseOffering co = CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum), Short.parseShort(sectionNum));

		return CoursesTeachingRepository.getInstance().findProfessorsForCourse(
                co.getCourseOfferingId());
	}

    public static void assignProfessorForCourse(final int professorId,
                                                final Season season,
                                                final short year, final String department,
                                                final String courseNum,
                                                final String sectionNum)
            throws SQLException {

        final CourseOffering co = CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum), Short.parseShort(sectionNum));

        CoursesTeachingRepository.getInstance().add(professorId,
                co.getCourseOfferingId());
    }

}
