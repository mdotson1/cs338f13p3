package model.time;

public class Date implements Comparable<Date> {

	private byte day;
	private byte month;
	private short year;
	
	private final byte DAYS_IN_WEEK = 7;
	
	public Date oneWeekThirty() {
		byte newDay;
		byte newMonth;
		
		final byte DAYS_IN_MONTH = 30;
		final byte MAXIMUM = DAYS_IN_MONTH - DAYS_IN_WEEK;
		
		if (day > MAXIMUM) { // we need to move up a month
    		newDay = (byte) (day - MAXIMUM);
    		newMonth = (byte) (month + 1);
    	} else {
    		newDay = (byte) (day + DAYS_IN_WEEK);
    		newMonth = month;
    	}
		return new Date(newDay, newMonth, year);
	}
	
	public Date oneWeekDecember() {
		byte newDay;
		byte newMonth;
		short newYear;
		
		final byte DAYS_IN_MONTH = 31;
		final byte MAXIMUM = DAYS_IN_MONTH - DAYS_IN_WEEK;
		
		if (day > MAXIMUM) { // we need to move up a month and one year
    		newDay = (byte) (day - MAXIMUM);
    		newMonth = 1; // january
    		newYear = (short) (year + 1);
    	} else {
    		newDay = (byte) (day + DAYS_IN_WEEK);
    		newMonth = month;
    		newYear = year;
    	}
		return new Date(newDay, newMonth, newYear);
	}
	
	public Date oneWeekFebruary() {
		byte newDay;
		byte newMonth;
		
		final byte DAYS_IN_MONTH;
		switch (year % 4) {
		case 0: 
			DAYS_IN_MONTH = 29;
			break;
		default:
			DAYS_IN_MONTH = 28;
			break;
		}
		
		final byte MAXIMUM = (byte) (DAYS_IN_MONTH - DAYS_IN_WEEK);
		
		if (day > MAXIMUM) { // we need to move up a month
    		newDay = (byte) (day - MAXIMUM);
    		newMonth = (byte) (month + 1);
    	} else {
    		newDay = (byte) (day + DAYS_IN_WEEK);
    		newMonth = month;
    	}
		return new Date(newDay, newMonth, year);
	}
	
	public Date oneWeekThirtyOne() {
		byte newDay;
		byte newMonth;
		
		final byte DAYS_IN_MONTH = 31;
		final byte MAXIMUM = DAYS_IN_MONTH - DAYS_IN_WEEK;
		
		if (day > MAXIMUM) { // we need to move up a month
    		newDay = (byte) (day - MAXIMUM);
    		newMonth = (byte) (month + 1);
    	} else {
    		newDay = (byte) (day + DAYS_IN_WEEK);
    		newMonth = month;
    	}
		return new Date(newDay, newMonth, year);
	}
	
	public Date oneWeekAfterStart() {
		switch (month) {
        case 1:  
            return oneWeekThirtyOne();
        case 2:
        	return oneWeekFebruary();
        case 3:  
        	return oneWeekThirtyOne();
        case 4:
        	return oneWeekThirty();
        case 5:
        	return oneWeekThirtyOne();
        case 6:
        	return oneWeekThirty();
        case 7:
        	// fall through
        case 8:
        	return oneWeekThirtyOne();
        case 9:
        	return oneWeekThirty();
        case 10:
        	return oneWeekThirtyOne();
        case 11:
        	return oneWeekThirty();
        case 12:
        	return oneWeekDecember();
        default:
        	return null;
		}
	}
	
	public Date(byte dd, byte mm, short yyyy)
	{
		day = dd;
		month = mm;
		year = yyyy;
	}
	
	public byte getDay() {
		return day;
	}
	
	public byte getMonth() {
		
		return month;
	}
	public short getYear() {
		return year;
	}

	@Override
	public int compareTo(Date d) {
		if (year == d.getYear()) {
			if (month == d.getMonth()) {
				if (day == d.getDay()) {
					return 0;
				} else {
					return day - d.getDay();
				}
			} else {
				return month - d.getMonth();
			}
		} else {
			return year - d.getYear();
		}
	}
}
