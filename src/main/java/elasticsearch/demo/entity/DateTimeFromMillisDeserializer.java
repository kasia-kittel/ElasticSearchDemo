package elasticsearch.demo.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class DateTimeFromMillisDeserializer extends JsonDeserializer<DateTime>
{

    private static final Logger log = Logger.getLogger(DateTimeFromMillisDeserializer.class.getName());

    @Override
    public DateTime deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {


        Long millis = jsonparser.getLongValue();
        log.info("serialized value: " + millis);

        DateTime datetime = new DateTime(millis);
        log.info("deserialized to: " + datetime);

        return datetime;
    }

}