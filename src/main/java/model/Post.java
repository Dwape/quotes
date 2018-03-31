package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Post {

    private String quote;

    private Date datePosted;

    private String description;

    @Id
    @GeneratedValue
    private long id;

    private long idBook;

    public Post(){}

    public Post(String quote, Date datePosted, String description, int idBook) {
        this.quote = quote;
        this.datePosted = datePosted;
        this.description = description;
        this.idBook = idBook;
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

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }
}
