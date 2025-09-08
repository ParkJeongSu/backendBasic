package kr.co.aim.infra.config;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 추후 openSearch 사용시 bean 생성
//@Configuration
public class OpenSearchConfig {
    //@Bean
    public RestHighLevelClient openSearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
    }
}
