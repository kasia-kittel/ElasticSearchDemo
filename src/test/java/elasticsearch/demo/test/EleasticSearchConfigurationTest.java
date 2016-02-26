package elasticsearch.demo.test;

import elasticsearch.demo.Application;
import elasticsearch.demo.entity.Tweet;
import elasticsearch.demo.repository.TweetRepository;
import elasticsearch.demo.utils.TweetBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EleasticSearchConfigurationTest {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static final Logger log = Logger.getLogger(EleasticSearchConfigurationTest.class.getName());

    @Test
    public void test() {
        Map mapping = elasticsearchTemplate.getMapping(Tweet.class);

        log.info(mapping.toString());

        DateTime now = new DateTime();
        log.info("Now in millis: " + now.getMillis());


        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        log.info("Now formatted: " + formatter.print(now));

        Tweet tweet = new TweetBuilder().date(now).build();
        Tweet created_tweet = tweetRepository.save(tweet);
        log.info("Created tweet dateTime: " + formatter.print(created_tweet.getDate()));

        assertEquals("Date of saved tweet should be equal with the date of the original tweet",
                    tweet.getDate(),
                    created_tweet.getDate()
                    );

    }


}