package fr.epita.datamodel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    private String username;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private Set<SeenMovie> seenMovies;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private Contact contact;

    public User(String username, Set<SeenMovie> seenMovies, Set<Role> roles, Contact contact) {
        this.username = username;
        this.seenMovies = seenMovies;
        this.roles = roles;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<SeenMovie> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<SeenMovie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", seenMovies=" + seenMovies +
                ", roles=" + roles +
                ", contact=" + contact +
                '}';
    }
}
