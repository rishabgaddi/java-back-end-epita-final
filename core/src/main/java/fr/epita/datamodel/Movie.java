package fr.epita.datamodel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "movie_id", unique = true)
//    private Set<SeenMovie> seenMovies;

    public Movie(Long id, String title, Date added, String externalId) {
        this.id = id;
        this.title = title;
        this.added = added;
        this.externalId = externalId;
//        this.seenMovies = seenMovies;
    }

    public Movie() {}

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

//    public Set<SeenMovie> getSeenMovies() {
//        return seenMovies;
//    }
//
//    public void setSeenMovies(Set<SeenMovie> seenMovies) {
//        this.seenMovies = seenMovies;
//    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", added=" + added +
                ", externalId='" + externalId + '\'' +
//                ", seenMovies=" + seenMovies +
                '}';
    }
}
