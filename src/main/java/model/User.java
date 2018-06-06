package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dwape on 3/26/18.
 */

@Entity
@Indexed
@Table(name = "user")
public class User {

    @Id
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
    //@DateTimeFormat(pattern = "MM-dd-yyyy")
    private String dateOfBirth;

    //look for a different way of doing this.
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private Set<Post> postArray = new HashSet<>();

    //comments written by the user
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private Set<Comment> commentArray = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private Set<Vote> voteArray = new HashSet<>();

    public User() {}

    public User(String username, String email, String password, String name, String surname, String dateOfBirth){
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Post> getPostArray() {
        return postArray;
    }

    /*
    public void setPostArray(Collection<Post> postArray) {
        this.postArray = postArray;
    }
    */

    public Set<Comment> getCommentArray() {
        return commentArray;
    }

    public Set<Vote> getVoteArray() {
        return voteArray;
    }
}
