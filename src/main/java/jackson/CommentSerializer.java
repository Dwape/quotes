package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hibernate.HibernateFactory;
import model.Comment;
import model.User;
import model.Vote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommentSerializer extends StdSerializer<Comment> {

    public CommentSerializer() {
        this(null);
    }

    public CommentSerializer(Class<Comment> c) {
        super(c);
    }

    @Override
    public void serialize(
            Comment comment, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", comment.getId());
        jgen.writeObjectField("commentArray", comment.getCommentArray());
        jgen.writeStringField("username", comment.getUser().getUsername());
        jgen.writeObjectField("datePosted", comment.getDatePosted());
        jgen.writeStringField("description", comment.getDescription());
        jgen.writeBooleanField("hasParent", comment.getParent() != null);

        //add if for not logged in users

        if (comment.getLoggedUsername() != null){
            List<User> users = comment.getVoteArray().stream()
                    .map(Vote::getUser)
                    .collect(Collectors.toList());
            List<String> usersVoted = users.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            List<Boolean> isPositive = comment.getVoteArray().stream()
                    .map(Vote::isPositive)
                    .collect(Collectors.toList());

            int index = usersVoted.indexOf(comment.getLoggedUsername());
            if (index != -1){
                jgen.writeBooleanField("loggedUserVote", isPositive.get(index));
            } else {
                jgen.writeObjectField("loggedUserVote", null); //check if it works
            }
        } else {
            jgen.writeObjectField("loggedUserVote", null); //check if it works
        }

        jgen.writeNumberField("score", comment.getScore());

        jgen.writeEndObject();
    }
}
