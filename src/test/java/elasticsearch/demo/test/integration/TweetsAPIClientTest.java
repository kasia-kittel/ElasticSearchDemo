package elasticsearch.demo.test.integration;

import com.google.gson.*;
import elasticsearch.demo.client.TweetsAPI;
import elasticsearch.demo.entity.Tweet;
import elasticsearch.demo.utils.TweetBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TweetsAPIClientTest {

    private static final Logger log = Logger.getLogger(TweetsAPIClientTest.class.getName());

    private final String TEST_URL = "http://localhost:8080";

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
            .create();

    private TweetsAPI tweeterService = new RestAdapter.Builder()
            .setEndpoint(TEST_URL)
            .setConverter(new GsonConverter(gson))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()
            .create(TweetsAPI.class);

    @Before
    public void cleanUp(){
        tweeterService.delete();

    }

    @Test
    public void shouldAddNewTweetToTweeterIndex() throws Exception {

        Tweet tweet = new TweetBuilder().build();

        Tweet created_tweet = tweeterService.add(tweet);
        assertEquals("Created tweet equals to sent tweet.", tweet, created_tweet);

        List<Tweet> tweets = tweeterService.getList();
        assertTrue("Tweet appears on the list of all tweets.", tweets.contains(tweet));

    }

    @Test
    public void shouldFindTweetById() throws Exception {
        Tweet tweet = new TweetBuilder().build();
        tweeterService.add(tweet);

        Tweet found_tweet = tweeterService.getById(tweet.getId());
        assertEquals("Should return tweet with given Id.", tweet, found_tweet);
    }

    private class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        @Override
        public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(src.getMillis());
        }
        @Override
        public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            return new DateTime(json.getAsLong());
        }
    }


}
