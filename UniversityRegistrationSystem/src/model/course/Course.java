package model.course;

import java.util.List;

public class Course {
	protected String department;
	protected short courseNumber;
	protected List<Course> prereqs;
	protected int cost;
	
	public Course(String dept, short courseNum, List<Course> prereqs, int cost) {
		department = dept;
		courseNumber = courseNum;
		this.prereqs = prereqs;
		this.cost = cost;
	}
}
