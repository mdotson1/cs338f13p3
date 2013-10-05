package com.cs388f13p1;

public class Semester {
	
	public enum Season {
	    FALL, WINTER, SUMMER, SPRING
	}
	
	private Season season;
	private short year;
	private Period period;
	
	public Semester(final Season s, final Period p) {
		season = s;
		year = p.startingYear();
		period = p;
	}
	
	public short getYear() {
		return year;
	}
	
	public void setYear(short year) {
		this.year = year;
	}
	
	public Season getSeason() {
		return season;
	}
	
	public void setSeason(Season s) {
		season = s;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public void setPeriod(Period p) {
		period = p;
	}
	
	public Date getStartDate() {
		return period.getStartDate();
	}
	
	public Date dropDate() {
		return period.getStartDate().oneWeekAfterStart();
	}
	
	public Date firstDayOfClasses() {
		return period.getStartDate();
	}

	public boolean equals(final Semester sem) {
		if (sem.getSeason() == season && sem.getYear() == year) {
			return true;
		} else {
			return false;
		}
	}

}
