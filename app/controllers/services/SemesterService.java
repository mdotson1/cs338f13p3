package controllers.services;

import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;

import java.sql.SQLException;
import java.util.Map;

public class SemesterService {

    public static void createSemester(final Map<String,String> data)
            throws SQLException {
        final Semester s = new Semester(
                Semester.Season.valueOf(data.get("Season")),
                Short.parseShort(data.get("Year")));

        SemesterRepository.getInstance().add(s);
    }
}
