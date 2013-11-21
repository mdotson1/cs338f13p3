package controllers.services;

import java.sql.SQLException;
import java.util.Map;

import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.ContactInformation;
import models.person.Professor;

public class ProfessorService {

	private ProfessorService() { } // impossible to instantiate a service

    public static void createProfessor(final Map<String,String> data)
            throws SQLException {

        final ContactInformation ci = new ContactInformation(
                data.get("Home Address"), data.get("Work Address"),
                data.get("First Name"), data.get("Last Name"),
                data.get("Work Phone"), data.get("Home Phone"),
                data.get("Cell Phone"));
        Professor p = new Professor(ci, data.get("Date of Birth"),
                data.get("Department"));

        ProfessorRepository.getInstance().add(p);
    }

    public static void createProfessor(final Map<String,String> data,
                                       final String department)
            throws SQLException {

        final ContactInformation ci = new ContactInformation(
                data.get("Home Address"), data.get("Work Address"),
                data.get("First Name"), data.get("Last Name"),
                data.get("Work Phone"), data.get("Home Phone"),
                data.get("Cell Phone"));
        Professor p = new Professor(ci, data.get("Date of Birth"),
                department);

        ProfessorRepository.getInstance().add(p);
    }

    /*
	public static boolean assignCourseForProfessor(int courseOfferingId,
			int professorId) throws SQLException {

		Professor p = CoursesTeachingRepository.getInstance().findProfessorForCourse(courseOfferingId);
		
		if (p == null) {
			
			CoursesTeachingRepository.getInstance().add(professorId, courseOfferingId);
			return true;
			} else {
			return false;
		}
	}
	*/
	
	public static boolean dropCourseFromProfessor(int professorId,
			int courseOfferingId) throws SQLException {
		
		return CoursesTeachingRepository.getInstance().delete(professorId, courseOfferingId);
	}
	
}
