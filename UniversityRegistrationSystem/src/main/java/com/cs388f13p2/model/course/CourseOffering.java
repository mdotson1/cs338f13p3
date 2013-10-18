package com.cs388f13p2.model.course;

public class CourseOffering {

	public enum Season {
		FALL, WINTER, SUMMER, SPRING
	}

	private int courseOfferingId;
	private Course course;
	private Season season;
	private short year;
	private short section;

	// when retrieving from database, has id
	public CourseOffering(final int courseOfferingId, final Course course,
			final Season season, final short year, final short section) {

		this.courseOfferingId = courseOfferingId;
		this.course = course;
		this.season = season;
		this.year = year;
		this.section = section;
	}
//TODO add department in the constructor	
	// when creating before adding to database
	public CourseOffering(final Course course, final Season season,
			final short year, final short section) {

		courseOfferingId = -1;
		this.course = course;
		this.season = season;
		this.year = year;
		this.section = section;
	}

	public int getCourseOfferingId() {
		return courseOfferingId;
	}

	public void setCourseOfferingId(int courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	

	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
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
