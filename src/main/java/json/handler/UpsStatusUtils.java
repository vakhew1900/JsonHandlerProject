package json.handler;

import java.util.List;
import java.util.stream.Collectors;

public class UpsStatusUtils {

    public static double avg(List<UpsStatus> upsStatuses){
        List<Integer> batteryRunTimeRemaingList = upsStatuses.stream().filter(n -> n.isBatteryRunTimeRemainingCorrect()).map(n -> n.getBatteryRunTimeRemaining()).collect(Collectors.toList());
        long sum = batteryRunTimeRemaingList.stream().mapToLong(n -> n).sum();
        return (double) sum / batteryRunTimeRemaingList.size();
    }


}
