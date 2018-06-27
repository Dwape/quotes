package model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name = "book")
public class Book {

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "datePublished")
    private String datePublished;

    @Column(name = "placePublished")
    private String placePublished;

    @Id
    @Column(name = "idBook")
    private String idBook;

    @OneToMany(mappedBy = "book", fetch=FetchType.LAZY)
    private Set<Post> postArray = new HashSet<>();

    public Book(){}

    public Book(String id, String title, String author){
        this.idBook = id;
        this.title = title;
        this.author = author;
        this.publisher = null;
        this.datePublished = null;
        this.placePublished = null;
        this.postArray = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String id) {
        this.idBook = id;
    }

    public Set<Post> getPostArray() {
        return postArray;
    }

    public void setPostArray(Set<Post> postArray) {
        this.postArray = postArray;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getPlacePublished() {
        return placePublished;
    }

    public void setPlacePublished(String placePublished) {
        this.placePublished = placePublished;
    }
}
