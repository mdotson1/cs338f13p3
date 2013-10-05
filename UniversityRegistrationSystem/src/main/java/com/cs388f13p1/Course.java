package com.cs388f13p1;

import java.util.List;

public class Course {
	
	protected int courseId;
	protected String department;
	protected short courseNumber;
	protected List<Course> prereqs;
	protected double cost;
	protected String courseDescription;
	
	public Course(final int courseId, final String dept, final short courseNum, 
			final List<Course> prereqs, final double cost,
			final String cd) {
		
		this.courseId = courseId;
		department = dept;
		courseNumber = courseNum;
		this.prereqs = prereqs;
		this.cost = cost;
		courseDescription = cd;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(final int courseId) {
		this.courseId = courseId;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(final String dpt) {
		department = dpt;
	}
	
	public short getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(final short cn) {
		courseNumber = cn;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(final short cost) {
		this.cost = cost;
	}
	
	public String getCourseDescription() {
		return courseDescription;
	}
	
	public void setCourseDescription(final String cd) {
		courseDescription = cd;
	}
}
