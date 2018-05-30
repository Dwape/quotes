package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.CommentSerializer;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
@JsonSerialize(using = CommentSerializer.class)
public class Comment {

    @Id
    @GeneratedValue
    @DocumentId
    private long id;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="idPost", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="parent")
    private Comment parent;

    //children
    @OneToMany(mappedBy = "parent", fetch=FetchType.EAGER)
    @OrderBy("score DESC")
    private Set<Comment> commentArray = new HashSet<>();

    @OneToMany(mappedBy = "comment", fetch=FetchType.EAGER)
    private Set<Vote> voteArray = new HashSet<>();

    private Date datePosted;

    @Column(length=5000)
    private String description;

    private int score;

    @Transient
    private String loggedUsername;

    public Comment(){}

    public Comment(User user, Post post, Comment parent, Date datePosted, String description){
        this.user = user;
        this.post = post;
        this.parent = parent;
        this.datePosted = datePosted;
        this.description = description;
        this.score = 0;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Set<Comment> getCommentArray() {
        return commentArray;
    }

    public void setCommentArray(Set<Comment> commentArray) {
        this.commentArray = commentArray;
    }

    public void setVoteArray(Set<Vote> voteArray) {
        this.voteArray = voteArray;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public Set<Vote> getVoteArray() {
        return voteArray;
    }

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }
}
