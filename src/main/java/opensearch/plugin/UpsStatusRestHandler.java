package opensearch.plugin;

import json.handler.UpsStatus;
import json.handler.UpsStatusUtils;
import org.opensearch.client.Node;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.node.NodeClient;
import org.opensearch.common.inject.Inject;
import org.opensearch.common.settings.Settings;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.BytesRestResponse;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestRequest;


import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;


public class UpsStatusRestHandler extends BaseRestHandler {

    @Override
    public String getName() {
        return "rest_handler_upsstatus";
    }

    @Override   
    public List routes() {
        return unmodifiableList(asList(
                new Route(RestRequest.Method.GET, "/_ups_status/{index}/{function}")));
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) {
        String index = request.param("index");
        String function = request.param("function");
        List<UpsStatus> upsStatusList = new DataFetcher(client).fetchUpsStatusesFromIndex(index);

        return channel -> {
            try {
                channel.sendResponse(UpsStatusService.buildResponse(function, upsStatusList));
            } catch (final Exception e) {
                channel.sendResponse(new BytesRestResponse(channel, e));
            }
        };
    }



}

