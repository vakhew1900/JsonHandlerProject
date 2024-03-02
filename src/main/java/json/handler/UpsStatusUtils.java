package json.handler;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UpsStatusUtils {

    public static double avg(List<UpsStatus> upsStatuses){
        List<Integer> batteryRunTimeRemaingList = upsStatuses.stream().filter(n -> Objects.nonNull(n.getBatteryRunTimeRemaining())).map(n -> n.getBatteryRunTimeRemaining()).collect(Collectors.toList());
        long sum = batteryRunTimeRemaingList.stream().mapToLong(n -> n).sum();
        return (double) sum / batteryRunTimeRemaingList.size();
    }

    public static int max(List<UpsStatus> upsStatuses){
        return upsStatuses.stream().filter(n -> Objects.nonNull(n.getOutputVoltage())).map(n -> n.getOutputVoltage()).max((m, n) -> Integer.compare(m, n)).get();
    }

    public static Set<String> values(List<UpsStatus> upsStatuses){
        return upsStatuses.stream().filter(n -> Objects.nonNull(n.getHost())).map(n -> n.getHost()).collect(Collectors.toSet());
    }
}
