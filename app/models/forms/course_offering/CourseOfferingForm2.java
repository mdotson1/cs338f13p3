package models.forms.course_offering;

import models.course.Course;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.SemesterRepository;
import play.data.validation.Constraints;

import java.sql.SQLException;

public class CourseOfferingForm2 {

    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short courseNumber;

    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short sectionNumber;

    public CourseOffering toCourseOffering(final String department,
                                           final Semester.Season season,
                                           final short year)
            throws SQLException {

        final CourseOffering co = new CourseOffering();
        co.setCourse(CourseRepository.getInstance().findById(department,
                courseNumber));
        co.setSemester(SemesterRepository.getInstance().findById(season, year));
        co.setSectionNumber(sectionNumber);

        return co;
    }
}
