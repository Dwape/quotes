package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    private String quote;

    private Date datePosted;

    private String description;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="idBook", nullable = false)
    private Book book;

    public Post(){}

    public Post(String quote, Date datePosted, String description, Book book, User user) {
        this.quote = quote;
        this.datePosted = datePosted;
        this.description = description;
        this.book = book;
        this.user = user;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser(){
        return user;
    } //check if it is necessary

    public void setUser(User user){
        this.user = user;
    } //check if it is necessary
}
