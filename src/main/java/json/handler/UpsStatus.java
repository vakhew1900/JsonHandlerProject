package json.handler;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsStatus {

    protected static final String UPS_ADV_OUTPUT_LOAD = "ups_adv_output_load";
    protected static final String UPS_ADV_BATTERY_TEMPERATURE = "ups_adv_battery_temperature";
    protected static final String TIMESTAMP = "@timestamp";
    protected static final String HOST = "host";
    protected static final String UPS_ADV_BATTERY_RUN_TIME_REMAINING = "ups_adv_battery_run_time_remaining";
    protected static final String UPS_ADV_OUTPUT_VOLTAGE = "ups_adv_output_voltage";


    @SerializedName(UPS_ADV_OUTPUT_LOAD)
    private Integer outputLoad = null;

    @SerializedName(UPS_ADV_BATTERY_TEMPERATURE)
    private Integer batteryTemperature = null;

    @SerializedName(TIMESTAMP)
    private LocalDateTime timestamp = null;

    @SerializedName(HOST)
    private String host = null;

    @SerializedName(UPS_ADV_BATTERY_RUN_TIME_REMAINING)
    private Integer batteryRunTimeRemaining = null;

    @SerializedName(UPS_ADV_OUTPUT_VOLTAGE)
    private Integer outputVoltage;

}
