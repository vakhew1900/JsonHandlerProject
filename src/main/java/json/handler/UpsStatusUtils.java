package json.handler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpsStatusUtils {

    public double avg(List<UpsStatus> upsStatuses){
        List<Integer> batteryRunTimeRemaingList = upsStatuses.stream().filter(n -> n.isBatteryRunTimeRemainingCorrect()).map(n -> n.getBatteryRunTimeRemaining()).collect(Collectors.toList());
        int sum = batteryRunTimeRemaingList.stream().mapToInt(n -> n).sum();
        return (double) sum / batteryRunTimeRemaingList.size();
    }
}
