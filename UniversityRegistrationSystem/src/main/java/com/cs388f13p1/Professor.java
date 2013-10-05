package com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Professor extends UniversityPerson {

	protected String department;
	protected List<CourseOffering> coursesTeaching;
	
	public Professor(ContactInformation ci, final int id, final String dob, 
			final String dept) {
		
		super(ci, id, dob);
		department = dept;
		coursesTeaching = new ArrayList<CourseOffering>();
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String dpt) {
		department = dpt;
	}
	
	public void addCourseTeaching(CourseOffering course) {
		coursesTeaching.add(course);
	}
	
	public boolean removeCourseTeaching(CourseOffering course) {
		return coursesTeaching.remove(course);
	}
	
	public Iterator<CourseOffering> getCoursesTeaching() {
		return coursesTeaching.iterator();
	}
}
