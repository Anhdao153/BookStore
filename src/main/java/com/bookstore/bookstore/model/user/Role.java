package com.bookstore.bookstore.model.user;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> appUsers;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AppUser> getAppUser() {
        return appUsers;
    }

    public void setAppUser(Set<AppUser> appUser) {
        this.appUsers = appUser;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appUser=" + appUsers +
                '}';
    }
}
