package models.course;

public class Semester {

    public enum Season {
        FALL, WINTER, SUMMER, SPRING
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
