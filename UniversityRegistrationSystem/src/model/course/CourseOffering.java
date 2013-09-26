package model.course;

import java.util.ArrayList;
import java.util.List;

import model.person.Professor;
import model.person.Student;
import model.time.Semester;

public class CourseOffering extends Course {

	private Semester semester;
	private Professor teacher;
	private short section;
	private List<Student> students;
	
	public CourseOffering(String dept, short courseNum, List<Course> prereqs, int cost,
			Semester sem, short section) {
		super(dept, courseNum, prereqs, cost);
		this.section = section;
		semester = sem;
		students = new ArrayList<Student>();
	}
	
	public Semester getSemester() {
		return semester;
	}
	
	public Professor getProfessor() {
		return teacher;
	}
	
	// true = removed prof, false = not removed
	public boolean removeProfessor() {
		if (teacher == null) {
			return false;
		} else {
			teacher = null;
			return true;
		}
	}
	
	// true = assigned prof, false = not assigned
	public boolean assignProfessor(Professor prof) {
		if (teacher == null) {
			teacher = prof;
			return true;
		} else {
			return false;
		}
	}
	
	public short getSectionNumber() {
		return section;
	}
	
	// true = added, false = not added
	public boolean addStudent(Student s) {
		if (students.size() == 10)
		{
			return false;
		}
		else
		{
			students.add(s);
			return true;
		}
	}
	
	public boolean dropStudent(Student student) {
		if (students.size() == 3) {
			return false;
		} else {
			students.remove(student);
			return true;
		}
	}
	
	public List<Student> getRoster() {
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
