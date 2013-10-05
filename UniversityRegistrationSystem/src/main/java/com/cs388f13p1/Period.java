package com.cs388f13p1;

public class Period {

	private Date startDate;
	private Date endDate;
	
	public Period(final Date start, final Date end) {
		startDate = start;
		setEndDate(end);
	}
	
	/**
	 * Returns the starting year for a period. For example,
	 * If we have a period 5/10/1990 - 12/2/2022, the year
	 * returned is 1990.
	 * @return the year of the starting date.
	 */
	public final short startingYear() {
		return startDate.getYear();
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
