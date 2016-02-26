package elasticsearch.demo;

import elasticsearch.demo.client.TweetsAPI;
import elasticsearch.demo.entity.Tweet;
import elasticsearch.demo.repository.TweetRepository;
import elasticsearch.demo.repository.TweetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class TweetsController implements TweetsAPI {

    private static final Logger log = Logger.getLogger(TweetsController.class.getName());

    @Autowired
    private TweetRepository repository;

    @Autowired
    private TweetRepositoryService tweetRepositoryService;

    @RequestMapping(value= TWEETS_PATH, method = RequestMethod.GET)
    public @ResponseBody List<Tweet> getList() {

        // We can use repository or service.
        // Default implementation of repository method returns
        // paged results.
        return tweetRepositoryService.findAllAsList();

    }

    @RequestMapping(value= TWEETS_PATH_ID, method = RequestMethod.GET)
    public @ResponseBody
    Tweet getById(@PathVariable("id") String id) {
        return repository.findOne(id);
    }

    @RequestMapping(value= TWEETS_PATH, method = RequestMethod.POST)
    public @ResponseBody
    Tweet add(@RequestBody Tweet e) {
        return tweetRepositoryService.save(e);
    }

    @RequestMapping(value= TWEETS_PATH, method = RequestMethod.DELETE)
    public @ResponseBody boolean delete() {
        repository.deleteAll();
        return true;
    }

}
