package elasticsearch.demo.repository;

import elasticsearch.demo.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Besides default repository we can create service with customised methods
 * using ElasticSearchTemplate
 */

@Service
public class TweetRepositoryService {

    private static final Logger log = Logger.getLogger(TweetRepositoryService.class.getName());

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * Default findAll implementation returns paged results.
     * Sometimes i.e. for test reason may be usefull to return
     * simple list of entities.
     * The list size will be limited to default page size.
     * (It returns first page)
     * For more customized resultset use scan or scroll instead.
     */
    public List<Tweet> findAllAsList() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build();

        return elasticsearchTemplate.queryForList(searchQuery, Tweet.class);
    }

    public Tweet save(Tweet e){
        return tweetRepository.save(e);
    }
}
