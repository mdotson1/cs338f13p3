package controllers;

import models.database.dao.concrete.StudentRepository;
import models.person.ContactInformation;
import models.person.Student;

import play.data.Form;
import play.mvc.*
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class AllStudents extends Controller {

    public static Result addStudent() {

    }

    public static Result removeStudent() {
        return ok();
    }

    public static Result updateStudent(final Integer id) {
        return ok();
    }
}
