package models.forms.semester;

import models.course.Semester;
import play.data.validation.Constraints;

public class SemesterForm1 {

    @Constraints.Required
    public String season;
    @Constraints.Min(message="Must be after 1970", value=1970)
    @Constraints.Max(message="Must be before 2099", value=2099)
    public short year;

    public Semester toSemester() {
        final Semester s = new Semester(Semester.Season.valueOf(season), year);

        return s;
    }
}
