package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Comment;

import java.io.IOException;

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
        jgen.writeEndObject();
    }
}
