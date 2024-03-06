package opensearch.plugin;

import com.google.gson.Gson;
import json.handler.UpsStatus;
import json.handler.UpsStatusParser;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.node.NodeClient;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.SearchHits;
import org.opensearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFetcher {

    private final NodeClient client;

    public DataFetcher(NodeClient client) {
        this.client = client;
    }

    public List<UpsStatus> fetchUpsStatusesFromIndex(String indexName) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest).actionGet();
        SearchHits hits = searchResponse.getHits();

        List<UpsStatus> upsStatusList = new ArrayList<>();
        Gson gson = UpsStatusParser.getGson();

        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            UpsStatus upsStatus = gson.fromJson(sourceAsString, UpsStatus.class);
            upsStatusList.add(upsStatus);
        }

        return upsStatusList;
    }
}
