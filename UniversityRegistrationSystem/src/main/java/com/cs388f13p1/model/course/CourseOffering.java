package com.cs388f13p1.model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cs388f13p1.model.person.Professor;
import com.cs388f13p1.model.person.Student;

public class CourseOffering extends Course {
	
	public enum Season {
	    FALL, WINTER, SUMMER, SPRING
	}

	private int courseOfferingId;
	private Season season;
	private short year;
	private Professor teacher;
	private short section;
	private List<Student> students;
	
	public CourseOffering(final String dept, final short courseNum, 
			final List<Course> prereqs, final int cost, final int courseOfferingId, final Season season,
			final short year, final short section, final String description) {
		
		super(dept, courseNum, prereqs, cost, description);
		this.courseOfferingId = courseOfferingId;
		this.season = season;
		this.year = year;
		this.section = section;
		students = new ArrayList<Student>();
	}
	
	public int getCourseOfferingId() {
		return courseOfferingId;
	}
	
	public void setCourseOfferingId(int courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	
	public Season getSeason() {
		return season;
	}
	
	public void setSeason(Season season) {
		this.season = season;
	}
	
	public short getYear() {
		return year;
	}
	
	public void setYear(short year) {
		this.year = year;
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
				+ "sectionNumber: " + section + "\n"
				+ "professor: " + teacher + "\n"
				+ "students: " + students + "\n";
		return retString;
	}
}
