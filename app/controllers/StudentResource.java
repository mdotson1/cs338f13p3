package controllers;

import models.database.dao.concrete.StudentRepository;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;

public class StudentResource extends Controller {
    public static Result getAllStudents() {
        try {
            return ok(student_table.render(StudentRepository.getInstance().getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addStudent() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getStudent(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result removeStudent() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updateStudent(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
