package elasticsearch.demo.test;

import elasticsearch.demo.entity.Tweet;
import elasticsearch.demo.utils.TweetBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TweetBuilderTest {

    @Test
    public void shouldBuildTweetWithRandomValuesWhenNoPropertiesSet(){

        Tweet e = new TweetBuilder().build();

        assertNotNull("Id should't be null.", e.getId());
        assertNotNull("UserId should't be null.", e.getUserId());
        assertNotNull("Message should't be null.", e.getMessage());
        assertNotNull("Date should't be null.", e.getDate());
    }

    @Test
    public void shouldBuildTweetWithGivenProperties(){

        String id = "idTest";
        String userId = "userIdTest";
        String actionType = "actionTypeTest";
        DateTime date = new DateTime().plusDays(1);
        String host = "hostTest";
        String service = "serviceTest";

        Tweet e = new TweetBuilder()
                .id(id)
                .userId(userId)
                .message(actionType)
                .date(date)
                .build();

        System.out.print(e);

        assertEquals("Id should be equals to the test value.", id, e.getId());
        assertEquals("UserId should be equals to the test value.", userId, e.getUserId());
        assertEquals("ActionType should be equals to the test value.", actionType, e.getMessage());
        assertEquals("Date should be equals to the test value.", date, e.getDate());
    }

}
