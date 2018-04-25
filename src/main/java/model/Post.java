package model;
import org.apache.lucene.analysis.commongrams.CommonGramsFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseTokenizerFactory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.shingle.ShingleFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Indexed
@AnalyzerDef(name = "customAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                }),
                @TokenFilterDef(factory= ShingleFilterFactory.class, params = {
                        @Parameter(name = "maxShingleSize", value = "10")
                })
        })
public class Post {

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    @Analyzer(definition = "customAnalyzer")
    //@Analyzer(impl = StandardAnalyzer.class)
    private String quote;

    private Date datePosted;

    private String description;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    @Id
    @GeneratedValue
    @DocumentId
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
