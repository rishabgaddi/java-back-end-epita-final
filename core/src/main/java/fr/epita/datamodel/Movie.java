package fr.epita.datamodel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private Date added;
    @Column(unique = true)
    private String externalId;

    public Movie(Long id, String title, Date added, String externalId) {
        this.id = id;
        this.title = title;
        this.added = added;
        this.externalId = externalId;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", added=" + added +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
