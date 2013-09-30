package main.java.com.cs388f13p1;

import java.util.ArrayList;
import java.util.List;

public class CourseOffering extends Course {

	private Semester semester;
	private Professor teacher;
	private short section;
	private List<Student> students;
	
	public CourseOffering(final String dept, final short courseNum, 
			final List<Course> prereqs, final int cost, final Semester sem,
			final short section) {
		
		super(dept, courseNum, prereqs, cost);
		this.section = section;
		semester = sem;
		students = new ArrayList<Student>();
	}
	
	public final Semester getSemester() {
		return semester;
	}
	
	public final Professor getProfessor() {
		return teacher;
	}
	
	public final void setProfessor(final Professor p) {
		teacher = p;
	}
	
	public final boolean addStudent(final Student s) {
		return students.add(s);
	}
	
	public final int getNumberOfStudents() {
		return students.size();
	}
	
	public final short getSectionNumber() {
		return section;
	}
	
	public final boolean removeStudent(final Student s) {
		return students.remove(s);
	}
	
	public final List<Student> getRoster() {
		return students;
	}
	
	public String toString() {
		String retString = "department: " + department + "\n"
				+ "courseNumber: " + courseNumber + "\n"
				+ "pre-reqs: " + prereqs + "\n"
				+ "cost: " + cost + "\n"
				+ "semester: " + semester + "\n"
				+ "sectionNumber: " + section + "\n"
				+ "professor: " + teacher + "\n"
				+ "students: " + students + "\n";
		return retString;
	}
}
