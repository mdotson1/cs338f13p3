package models.course;

public class Course {
	
	protected String department; // primary key
	protected short courseNumber; // primary key
	protected double cost;
	protected String courseDescription;
	
	public Course(final String dept, final short courseNum, 
			final double cost, final String cd) {
		
		department = dept;
		courseNumber = courseNum;
		this.cost = cost;
		courseDescription = cd;
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
