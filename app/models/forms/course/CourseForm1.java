package models.forms.course;

import models.course.Course;
import models.forms.FormUtils;
import play.data.validation.Constraints;

public class CourseForm1 {

    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short courseNumber;
    @Constraints.Required()
    @Constraints.Pattern(value = FormUtils.MONEY_REGEX, message = "Please enter cost with 2 decimal places and up to 4 digits before the decimal")
    public String cost;
    @Constraints.Required()
    @Constraints.MinLength(value = 1, message="Please enter a course description")
    @Constraints.MaxLength(value = 100, message="Course description cannot be more than 100 letters")
    public String courseDescription;

    public Course toCourse(final String department) {
        final Course c = new Course(department, courseNumber,
                Double.parseDouble(cost), courseDescription);

        return c;
    }
}
