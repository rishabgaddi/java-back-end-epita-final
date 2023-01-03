package fr.epita.datamodel;

import java.util.Date;

public class Movie {
    private String title;
    private Date added;
    private String externalId;

    public Movie(String title, Date added, String externalId) {
        this.title = title;
        this.added = added;
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", added=" + added +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
