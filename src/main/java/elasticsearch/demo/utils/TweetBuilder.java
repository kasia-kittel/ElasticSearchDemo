package elasticsearch.demo.utils;

import elasticsearch.demo.entity.Tweet;
import org.joda.time.DateTime;

import java.util.Random;
import java.util.UUID;


public class TweetBuilder {

    private Random rand = new Random();

    private Tweet tweet;

    private String id = null;
    private String userId = null;
    private DateTime date = null;
    private String message = null;

    public TweetBuilder id (String id){
        this.id = id;
        return this;
    }

    public TweetBuilder userId (String id){
        this.userId = id;
        return this;
    }

    public TweetBuilder date (DateTime date){
        this.date = date;
        return this;
    }

    public TweetBuilder message(String message){
        this.message = message;
        return this;
    }

    public Tweet build(){

        tweet = new Tweet();

        if (id == null){
            tweet.setId(UUID.randomUUID().toString());
        }
        else {
            tweet.setId(id);
        }

        if (userId == null ){
            tweet.setUserId("user-" + UUID.randomUUID().toString());
        }
        else {
            tweet.setUserId(userId);
        }

        if (date == null ){
            tweet.setDate(new DateTime().plusDays(rand.nextInt(10) + 1));
        }
        else {
            tweet.setDate(date);
        }

        if (message == null) {
            tweet.setMessage("message-" + (rand.nextInt(10) + 1));
        }
        else {
            tweet.setMessage(message);
        }

        return tweet;
    }

}
