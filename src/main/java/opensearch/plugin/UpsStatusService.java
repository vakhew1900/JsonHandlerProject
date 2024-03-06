package opensearch.plugin;

import json.handler.UpsStatus;
import json.handler.UpsStatusUtils;
import org.opensearch.rest.BytesRestResponse;
import org.opensearch.rest.RestResponse;
import org.opensearch.rest.RestStatus;

import java.util.List;
import java.util.Set;

public class UpsStatusService {

    public static RestResponse buildResponse(String function, List<UpsStatus> upsStatuses){
        RestResponse  response;
        String message = "message";
        RestStatus status = RestStatus.OK;

        try {
            switch (function) {
                case UpsStatusUtils.AVG:
                    double avg = UpsStatusUtils.avg(upsStatuses);
                    message = "result: avg = " + avg;
                    break;
                case UpsStatusUtils.MAX:
                    int max = UpsStatusUtils.max(upsStatuses);
                    message = "result: max = " + max;
                    break;
                case UpsStatusUtils.VALUES:
                    Set<String> values = UpsStatusUtils.values(upsStatuses);
                    message = "result: values = " + values;
                    break;
                default:
                    status = RestStatus.NOT_FOUND;
                    message = "funtion not found in plugin";
                    break;
            }
        }
        catch (RuntimeException e){
            status = RestStatus.BAD_REQUEST;
            message = e.getMessage();
        }

        return  new BytesRestResponse(status, message);
    }
}
