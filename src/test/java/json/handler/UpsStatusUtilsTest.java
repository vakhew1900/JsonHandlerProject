package json.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class UpsStatusUtilsTest {


    @Test
    void maxEmptyList() {
        List<UpsStatus> upsStatusList = new ArrayList<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> UpsStatusUtils.max(upsStatusList));
    }

    @Test
    void avgEmptyList() {
        List<UpsStatus> upsStatusList = new ArrayList<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> UpsStatusUtils.avg(upsStatusList));
    }

    @Test
    void valueEmptyList() {
        List<UpsStatus> upsStatusList = new ArrayList<>();
        Assertions.assertEquals(new HashSet<>(), UpsStatusUtils.values(upsStatusList));
    }

    @Test
    void maxOneElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();
        int value = 10;

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setOutputVoltage(value);
        upsStatusList.add(upsStatus);

        Assertions.assertEquals(value, UpsStatusUtils.max(upsStatusList));
    }

    @Test
    void avgOneElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();
        int value = 10;

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setBatteryRunTimeRemaining(value);
        upsStatusList.add(upsStatus);

        Assertions.assertEquals(value, UpsStatusUtils.avg(upsStatusList));
    }

    @Test
    void valuesOneElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();

        HashSet<String>  values= new HashSet<>(Arrays.asList("host"));

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setHost("host");
        upsStatusList.add(upsStatus);

        Assertions.assertEquals(values, UpsStatusUtils.values(upsStatusList));
    }

    @Test
    void maxTwoElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setOutputVoltage(10);
        upsStatusList.add(upsStatus);

        UpsStatus upsStatus2 = new UpsStatus();
        upsStatus2.setOutputVoltage(20);
        upsStatusList.add(upsStatus2);
        int expectedValue = 20;
        Assertions.assertEquals(expectedValue, UpsStatusUtils.max(upsStatusList));
    }

    @Test
    void avgTwoElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setBatteryRunTimeRemaining(10);
        upsStatusList.add(upsStatus);

        UpsStatus upsStatus2 = new UpsStatus();
        upsStatus2.setBatteryRunTimeRemaining(20);
        upsStatusList.add(upsStatus2);
        int expectedValue = 15;
        Assertions.assertEquals(expectedValue, UpsStatusUtils.avg(upsStatusList));
    }

    @Test
    void valuesTwoElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();
        final HashSet<String>  values= new HashSet<>(Arrays.asList("host", "192.168.1.4"));

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setHost("host");
        upsStatusList.add(upsStatus);

        UpsStatus upsStatus2 = new UpsStatus();
        upsStatus2.setHost("192.168.1.4");
        upsStatusList.add(upsStatus2);

        Assertions.assertEquals(values, UpsStatusUtils.values(upsStatusList));
    }


    @Test
    void valuesTwoSameElement(){
        List<UpsStatus> upsStatusList = new ArrayList<>();
        final HashSet<String>  values= new HashSet<>(Arrays.asList("host"));

        UpsStatus upsStatus = new UpsStatus();
        upsStatus.setHost("host");
        upsStatusList.add(upsStatus);

        UpsStatus upsStatus2 = new UpsStatus();
        upsStatus2.setHost("host");
        upsStatusList.add(upsStatus2);

        Assertions.assertEquals(values, UpsStatusUtils.values(upsStatusList));
    }
}
