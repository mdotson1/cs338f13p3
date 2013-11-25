package models.course;

import play.data.validation.Constraints;

public class Semester {

    public enum Season {
        Fall, Winter, Summer, Spring
    }

    private Season season;
    private short year;

    // bean for play
    public Semester() {

    }

    public Semester(final Season season, final short year) {
        this.season = season;
        this.year = year;
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
}
