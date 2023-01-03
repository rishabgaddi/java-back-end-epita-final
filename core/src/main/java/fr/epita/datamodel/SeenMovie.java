package fr.epita.datamodel;

import java.util.Date;

public class SeenMovie {
    private Date date;

    public SeenMovie(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SeenMovie{" +
                "date=" + date +
                '}';
    }
}
