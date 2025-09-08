package kr.co.aim.api.rtd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.common.rule.RuleContext;
import kr.co.aim.infra.persistence.entity.CarriersEntity;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

//@Service
@RequiredArgsConstructor
public class HistoryLogService {
    private final RestHighLevelClient openSearchClient;

    public void saveHistory(String transactionId, List<RuleContext<CarriersEntity>> history) throws IOException {
        int i=0;
        for (RuleContext<CarriersEntity> step : history) {
            ObjectMapper objectMapper = new ObjectMapper();
            step.setStepNumber(i++);
            step.setTransactionId(transactionId);
            String jsonString = objectMapper.writeValueAsString(step);
            IndexRequest request = new IndexRequest("decision-history").source(jsonString, XContentType.JSON);
            openSearchClient.index(request, RequestOptions.DEFAULT);
        }
    }
}
