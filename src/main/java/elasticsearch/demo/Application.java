package elasticsearch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@EnableElasticsearchRepositories(basePackages = "elasticsearch.demo.repository")
@SpringBootApplication
public class Application {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {

        ElasticsearchTemplate esTemplate = new ElasticsearchTemplate(nodeBuilder().local(true).node().client());

        //Other way to inject mapping to ES
        //esTemplate.createIndex(Tweet.class);
        //esTemplate.putMapping(Tweet.class);

        return esTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
