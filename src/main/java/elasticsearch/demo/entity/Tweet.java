package elasticsearch.demo.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "twitter", type = "tweet", shards = 1, replicas = 0)
public class Tweet {

    @Id
    private String id;
    protected String userId;

    @JsonSerialize(using=DateTimeToMillisSerializer.class)
    @JsonDeserialize(using=DateTimeFromMillisDeserializer.class)
    //@Field(type = FieldType.Date) this seems broken
    protected DateTime date;

    protected String message;


    public Tweet(String id, String userId, DateTime date, String message) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.message = message;
    }

    public Tweet() {}

    public String getId() { return id;}

    public void setId(String id) { this.id = id; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equal(id, tweet.id) &&
                Objects.equal(userId, tweet.userId) &&
                Objects.equal(date, tweet.date) &&
                Objects.equal(message, tweet.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, userId, message);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
