package com.cs388f13p2.model.course;

public class CourseOffering {
	
	public enum Season {
	    FALL, WINTER, SUMMER, SPRING
	}

	private Course course;
	private int courseOfferingId;
	private Season season;
	private short year;
	private short section;
	
	public CourseOffering(final Course course, final int courseOfferingId, final Season season,
			final short year, final short section, final String description) {
		
		this.course = course;
		this.courseOfferingId = courseOfferingId;
		this.season = season;
		this.year = year;
		this.section = section;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
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
	
	public short getSectionNumber() {
		return section;
	}
	
	public void setSectionNumber(short section) {
		this.section = section;
	}
}
