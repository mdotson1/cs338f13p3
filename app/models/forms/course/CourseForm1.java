package models.forms.course;

import models.course.Course;
import models.forms.FormUtils;
import play.data.validation.Constraints;

import java.text.DecimalFormat;

public class CourseForm1 {

    @Constraints.Required()
    @Constraints.Min(value = 1, message="Please enter a course number greater than 0")
    @Constraints.Max(value = 999, message="Please enter a course number less than 999")
    public short courseNumber;
    @Constraints.Required()
    @Constraints.Min(value=1, message="Please enter at least 1 USD")
    @Constraints.Max(value = 9998, message="Please enter a value less than 9999")
    public double cost;
    @Constraints.Required()
    @Constraints.MinLength(value = 1, message="Please enter a course description")
    @Constraints.MaxLength(value = 100, message="Course description cannot be more than 100 letters")
    public String courseDescription;

    final static double roundTwoDecimals(final double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public Course toCourse(final String department) {
        final Course c = new Course(department, courseNumber,
                roundTwoDecimals(cost), courseDescription);

        return c;
    }
}
