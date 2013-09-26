package model.time;

public class Semester {
	
	public enum Season {
	    FALL, WINTER, SUMMER, SPRING
	}
	
	private Season season;
	private short year;
	private Period period;
	
	public Semester(Season s, Period p) {
		season = s;
		year = p.startingYear();
		period = p;
	}
	
	public short getYear() {
		return year;
	}
	
	public Season getSeason() {
		return season;
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

}
