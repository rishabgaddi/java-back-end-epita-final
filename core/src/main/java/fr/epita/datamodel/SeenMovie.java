package fr.epita.datamodel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEEN_MOVIES")
public class SeenMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", unique = true)
    private Movie movie;

    public SeenMovie(Long id, Date date, User user, Movie movie) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.movie = movie;
    }

    public SeenMovie() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "SeenMovie{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
