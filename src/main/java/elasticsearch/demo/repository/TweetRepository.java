package elasticsearch.demo.repository;

import elasticsearch.demo.entity.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * We can use default Repository implementation for given entity type
 */

@Repository
public interface TweetRepository extends ElasticsearchRepository<Tweet,String> {}