package main.java.com.cs388f13p1;

import java.util.List;

public class Course {
	protected String department;
	protected short courseNumber;
	protected List<Course> prereqs;
	protected int cost;
	
	public Course(final String dept, final short courseNum, 
			final List<Course> prereqs, final int cost) {
		
		department = dept;
		courseNumber = courseNum;
		this.prereqs = prereqs;
		this.cost = cost;
	}
	
	public final String getDepartment() {
		return department;
	}
	
	public final short getCourseNumber() {
		return courseNumber;
	}
	
	public final int getCost() {
		return cost;
	}
}
