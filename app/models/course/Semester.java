package models.course;

import play.data.validation.Constraints;

import java.util.LinkedHashMap;
import java.util.Map;

public class Semester {

    public enum Season {
        Spring, Summer, Fall, Winter
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

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Semester) {
            final Semester sem = (Semester) obj;
            if (sem.getSeason() == season && sem.getYear() == year) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
