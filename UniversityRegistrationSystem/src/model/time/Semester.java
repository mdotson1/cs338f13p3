package model.time;

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
	
	public final short getYear() {
		return year;
	}
	
	public final Season getSeason() {
		return season;
	}
	
	public final Date getStartDate() {
		return period.getStartDate();
	}
	
	public final Date dropDate() {
		return period.getStartDate().oneWeekAfterStart();
	}
	
	public final Date firstDayOfClasses() {
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
