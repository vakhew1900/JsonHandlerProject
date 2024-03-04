package json.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UpsStatusDeserializerTest {

    private static Gson gson;

    @BeforeAll
    public static void setup() {
        gson = new GsonBuilder()
                .registerTypeAdapter(UpsStatus.class, new UpsStatusDeserializer())
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .create();

    }


    @Test
    public void emptyJson() {
        String json = "{" +
                "}";

        UpsStatus upsStatus = gson.fromJson(json, UpsStatus.class);
        UpsStatus expectedUpsStatus = new UpsStatus(null, null, null, null, null, null);
        Assertions.assertEquals(upsStatus, expectedUpsStatus);
    }

    @Test
    public void emptyFieldInJson() {
        String json = "{" +
                "\"ups_adv_output_load\": 6," +
                "\"ups_adv_battery_temperature\": 23," +
                "\"@timestamp\": \"2021-04-20T15:34:58.290Z\"," +
                "\"host\": \"192.168.11.9\"," +
                "\"ups_adv_battery_run_time_remaining\": 528000" +
                //  .append(" \"ups_adv_output_voltage\": 234")
                "}";

        UpsStatus upsStatus = gson.fromJson(json, UpsStatus.class);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse("2021-04-20T15:34:58.290Z", dateTimeFormatter);
        UpsStatus expectedUpsStatus = new UpsStatus(6, 23, localDateTime, "192.168.11.9", 528000, null);
        Assertions.assertEquals(upsStatus, expectedUpsStatus);
    }

    @Test
    public void typeTest() {
        String json = "{" +
                "\"ups_adv_output_load\": 6," +
                "\"ups_adv_battery_temperature\": 23," +
                "\"@timestamp\": \"2021-04-20T15:34:58.290Z\"," +
                "\"host\": \"192.168.11.9\"," +
                "\"ups_adv_battery_run_time_remaining\": 528000," +
                " \"ups_adv_output_voltage\": 234" +
                "}";

        UpsStatus upsStatus = gson.fromJson(json, UpsStatus.class);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse("2021-04-20T15:34:58.290Z", dateTimeFormatter);
        UpsStatus expectedUpsStatus = new UpsStatus(6, 23, localDateTime, "192.168.11.9", 528000, 234);
        Assertions.assertEquals(upsStatus, expectedUpsStatus);
    }


    @Test
    public void incorrectFieldFormat() {
        String json = "{" +
                "\"ups_adv_output_load\": \"fff\"," +
                "\"ups_adv_battery_temperature\": 23," +
                "\"@timestamp\": \"2021-04-20T15:34:58.290Z\"," +
                "\"host\": \"192.168.11.9\"," +
                "\"ups_adv_battery_run_time_remaining\": 528000," +
                " \"ups_adv_output_voltage\": 234" +
                "}";

        UpsStatus upsStatus = gson.fromJson(json, UpsStatus.class);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse("2021-04-20T15:34:58.290Z", dateTimeFormatter);
        UpsStatus expectedUpsStatus = new UpsStatus(null, 23, localDateTime, "192.168.11.9", 528000, 234);
        Assertions.assertEquals(upsStatus, expectedUpsStatus);
    }

    @Test
    public void incorrectTimeFieldFormat() {
        String json = "{" +
                "\"ups_adv_output_load\": 6," +
                "\"ups_adv_battery_temperature\": 23," +
                "\"@timestamp\": \"2021-04-20T15:34\"," +
                "\"host\": \"192.168.11.9\"," +
                "\"ups_adv_battery_run_time_remaining\": 528000," +
                " \"ups_adv_output_voltage\": 234" +
                "}";

        UpsStatus upsStatus = gson.fromJson(json, UpsStatus.class);
        UpsStatus expectedUpsStatus = new UpsStatus(6, 23, null, "192.168.11.9", 528000, 234);
        Assertions.assertEquals(upsStatus, expectedUpsStatus);
    }
}
