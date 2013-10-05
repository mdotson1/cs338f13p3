package com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseOffering extends Course {

	private int courseOfferingId;
	private Semester semester;
	private Professor teacher;
	private short section;
	private List<Student> students;
	
	public CourseOffering(final int courseId, final String dept, final short courseNum, 
			final List<Course> prereqs, final int cost, final int courseOfferingId, final Semester sem,
			final short section, final String description) {
		
		super(courseId, dept, courseNum, prereqs, cost, description);
		this.courseOfferingId = courseOfferingId;
		this.section = section;
		semester = sem;
		students = new ArrayList<Student>();
	}
	
	public int getCourseOfferingId() {
		return courseOfferingId;
	}
	
	public void setCourseOfferingId(int courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	
	public Semester getSemester() {
		return semester;
	}
	
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public Professor getProfessor() {
		return teacher;
	}
	
	public void setProfessor(final Professor p) {
		teacher = p;
	}
	
	public boolean addStudent(final Student s) {
		return students.add(s);
	}
	
	public int getNumberOfStudents() {
		return students.size();
	}
	
	public short getSectionNumber() {
		return section;
	}
	
	public void setSectionNumber(short section) {
		this.section = section;
	}
	
	public boolean removeStudent(final Student s) {
		return students.remove(s);
	}
	
	public Iterator<Student> getRoster() {
		return students.iterator();
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
