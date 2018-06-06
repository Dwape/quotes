package model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "idVote")
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idPost")
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idComment")
    private Comment comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username", nullable = false)
    private User user;

    private boolean isPositive;

    public Vote() {
    }

    public Vote(Post post, Comment comment, User user, boolean isPositive) {
        this.post = post;
        this.comment = comment;
        this.user = user;
        this.isPositive = isPositive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }*/

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }
}
