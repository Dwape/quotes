package model;

import securityFilter.SecurityConfig;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Dwape on 3/26/18.
 */

@Entity
@Table(name = "user", indexes = @Index(name = "username", columnList = "username", unique = true))
public class User {

    @Id @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Transient
    private List<String> roles = new ArrayList<>();

    @OneToMany
    @JoinTable(name="USER_POST_RELATION")
    private Collection<Post> postArray = new ArrayList<>();

    public User() {roles.add(SecurityConfig.ROLE_USER);}

    public User(String username, String email, String password, String name, String surname, Date dateOfBirth){
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        roles.add(SecurityConfig.ROLE_USER);
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Collection<Post> getPostArray() {
        return postArray;
    }

    public void setPostArray(Collection<Post> postArray) {
        this.postArray = postArray;
    }
}
