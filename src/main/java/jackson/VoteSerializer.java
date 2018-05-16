package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import model.Comment;
import model.Vote;

import java.io.IOException;

public class VoteSerializer extends StdSerializer<Vote> {

    public VoteSerializer() {
        this(null);
    }

    public VoteSerializer(Class<Vote> c) {
        super(c);
    }

    @Override
    public void serialize(Vote vote, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        /*jgen.writeStartObject();
        jgen.writeNumberField("id", vote.getId());
        jgen.writeStringField("username", vote.getUser().getUsername());
        jgen.writeBooleanField("isPositive", vote.isPositive());
        jgen.writeEndObject();*/
    }
}
