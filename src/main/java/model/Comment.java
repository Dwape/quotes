package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @DocumentId
    private long id;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="idPost", nullable = false)
    private Post post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent")
    private Comment parent;

    //children
    @OneToMany(mappedBy = "parent", fetch=FetchType.EAGER)
    private Set<Comment> commentArray = new HashSet<>();

    private Date datePosted;

    private String description;

    @Column(name = "hasParent")
    private boolean hasParent;

    public Comment(){}

    public Comment(User user, Post post, Comment parent, Date datePosted, String description){
        this.user = user;
        this.post = post;
        this.parent = parent;
        this.datePosted = datePosted;
        this.description = description;
        this.hasParent = (parent != null);
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

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }
}