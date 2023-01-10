package fr.epita.datamodel;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @Column
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
