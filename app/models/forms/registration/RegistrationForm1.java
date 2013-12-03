package models.forms.registration;

import models.course.Course;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import play.data.validation.Constraints;

import java.sql.SQLException;
import java.util.*;

import models.course.Semester.Season;

public class RegistrationForm1 {

    public Map<Integer,CourseOffering> toCourseOfferings(final Season season,
                                                         final short year) {
        final Map<Integer,CourseOffering> cos =
                new LinkedHashMap<Integer,CourseOffering>();

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(firstChoiceDepartment,
                    firstChoiceCourseNum, firstChoiceSectionNum, season, year);
            cos.put(1, co);
        } catch (SQLException e) {
            cos.put(1, null);
        }

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(secondChoiceDepartment,
                    secondChoiceCourseNum, secondChoiceSectionNum, season, year);
            cos.put(2, co);
        } catch (SQLException e) {
            cos.put(2, null);
        }

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(thirdChoiceDepartment,
                    thirdChoiceCourseNum, thirdChoiceSectionNum, season, year);
            cos.put(3, co);
        } catch (SQLException e) {
            cos.put(3, null);
        }

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(fourthChoiceDepartment,
                    fourthChoiceCourseNum, fourthChoiceSectionNum, season, year);
            cos.put(4, co);
        } catch (SQLException e) {
            cos.put(4, null);
        }

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(fifthChoiceDepartment,
                    fifthChoiceCourseNum, fifthChoiceSectionNum, season, year);
            cos.put(5, co);
        } catch (SQLException e) {
            cos.put(5, null);
        }

        try {
            final CourseOffering co = CourseOfferingRepository.
                    getInstance().find(sixthChoiceDepartment,
                    sixthChoiceCourseNum, sixthChoiceSectionNum, season, year);
            cos.put(6, co);
        } catch (SQLException e) {
            cos.put(6, null);
        }

        return cos;

    }

    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String firstChoiceDepartment;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short firstChoiceCourseNum;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short firstChoiceSectionNum;


    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String secondChoiceDepartment;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short secondChoiceCourseNum;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short secondChoiceSectionNum;


    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String thirdChoiceDepartment;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short thirdChoiceCourseNum;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short thirdChoiceSectionNum;


    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String fourthChoiceDepartment;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short fourthChoiceCourseNum;
    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short fourthChoiceSectionNum;


    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String fifthChoiceDepartment;
    @Constraints.Required
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short fifthChoiceCourseNum;
    @Constraints.Required
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short fifthChoiceSectionNum;


    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 4 letters", value=4)
    public String sixthChoiceDepartment;
    @Constraints.Required
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short sixthChoiceCourseNum;
    @Constraints.Required
    @Constraints.Min(value = 1, message="Please enter a section number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a section number less than 999")
    public short sixthChoiceSectionNum;
}
