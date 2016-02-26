package elasticsearch.demo.client;

import elasticsearch.demo.entity.Tweet;

import retrofit.http.*;

import java.util.List;


public interface TweetsAPI {

    public static final String TWEETS_PATH = "/tweets";
    public static final String TWEETS_PATH_ID = "/tweets/{id}";

    @GET(TWEETS_PATH)
    public List<Tweet> getList();

    @GET(TWEETS_PATH_ID)
    public Tweet getById(@Path("id") String id);

    @POST(TWEETS_PATH)
    public Tweet add(@Body Tweet e);

    @DELETE(TWEETS_PATH)
    public boolean delete();

}
