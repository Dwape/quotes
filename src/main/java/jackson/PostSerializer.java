package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Post;
import model.User;
import model.Vote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PostSerializer extends StdSerializer<Post> {

    public PostSerializer() {
        this(null);
    }

    public PostSerializer(Class<Post> p) {
        super(p);
    }

    @Override
    public void serialize(
            Post post, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getId());
        jgen.writeStringField("quote", post.getQuote());
        jgen.writeStringField("description", post.getDescription());
        jgen.writeStringField("postedBy", post.getUser().getUsername());
        jgen.writeStringField("bookTitle", post.getBook().getTitle());
        jgen.writeStringField("bookAuthor", post.getBook().getAuthor());
        jgen.writeObjectField("datePosted", post.getDatePosted());

        if (post.getLoggedUsername() != null){
            List<User> users = post.getVoteArray().stream()
                    .map(Vote::getUser)
                    .collect(Collectors.toList());
            List<String> usersVoted = users.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            List<Boolean> isPositive = post.getVoteArray().stream()
                    .map(Vote::isPositive)
                    .collect(Collectors.toList());

            int index = usersVoted.indexOf(post.getLoggedUsername());
            if (index != -1){
                jgen.writeBooleanField("loggedUserVote", isPositive.get(index));
            } else {
                jgen.writeObjectField("loggedUserVote", null); //check if it works
            }
        } else {
            jgen.writeObjectField("loggedUserVote", null); //check if it works
        }

        jgen.writeNumberField("score", post.getScore());

        jgen.writeEndObject();
    }
}