package model.time;

public class Date {

	private byte day;
	private byte month;
	private short year;
	
	public Date(byte dd, byte mm, byte yyyy)
	{
		day = dd;
		month = mm;
		year = yyyy;
	}
	
	public short getYear() {
		return year;
	}
}
