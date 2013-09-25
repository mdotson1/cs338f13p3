package model.time;

public class Date {

	private byte day;
	private byte month;
	private short year;
	
	public Date(byte dd, byte mm, short yyyy) throws Exception
	{
		switch (mm) {
        case 1:  
        	if (dd > 31 && dd < 59) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 59) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 2:
        	if (dd > 28 && dd < 59) {
        		day = (byte) (dd - 28);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 59) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 3:  
        	if (dd > 31 && dd < 61) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 4:
        	if (dd > 30 && dd < 61) {
        		day = (byte) (dd - 30);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 5:
        	if (dd > 31 && dd < 61) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 6:
        	if (dd > 30 && dd < 61) {
        		day = (byte) (dd - 30);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 7:
        	if (dd > 31 && dd < 62) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 62) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 8:
        	if (dd > 31 && dd < 61) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 9:
        	if (dd > 30 && dd < 61) {
        		day = (byte) (dd - 30);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 10:
        	if (dd > 31 && dd < 61) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 11:
        	if (dd > 30 && dd < 61) {
        		day = (byte) (dd - 30);
        		month = mm++;
        		year = yyyy;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 61) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
        case 12:
        	if (dd > 31 && dd < 62) {
        		day = (byte) (dd - 31);
        		month = mm++;
        		year = yyyy++;
        	} else if (dd < 1) {
        		
        	} else if (dd >= 62) {
        		
        	} else {
        		day = dd;
        		month = mm;
        		year = yyyy;
        	}
                 break;
		}
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
}
