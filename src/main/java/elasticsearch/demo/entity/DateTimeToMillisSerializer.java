package elasticsearch.demo.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class DateTimeToMillisSerializer extends JsonSerializer<DateTime>{

    private static final Logger log = Logger.getLogger(DateTimeToMillisSerializer.class.getName());

    @Override
    public void serialize(DateTime date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        gen.writeNumber(date.getMillis());
    }
}