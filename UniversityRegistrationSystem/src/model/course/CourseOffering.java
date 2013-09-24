package model.course;

import java.util.List;

import model.person.Professor;
import model.time.Semester;

public class CourseOffering extends Course {

	private Semester semester;
	private Professor teacher;
	private short section;
	
	public CourseOffering(String dept, short courseNum, List<Course> prereqs, int cost,
			Semester sem, short section) {
		super(dept, courseNum, prereqs, cost);
		this.section = section;
		semester = sem;
	}
	
	public void assignTeacher(Professor prof) {
		teacher = prof;
	}
	
	public short getSectionNumber() {
		return section;
	}
}
