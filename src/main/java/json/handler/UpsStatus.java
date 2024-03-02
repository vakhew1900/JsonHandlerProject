package json.handler;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpsStatus {

    protected static final String UPS_ADV_OUTPUT_LOAD = "ups_adv_output_load";
    protected static final String UPS_ADV_BATTERY_TEMPERATURE = "ups_adv_battery_temperature";
    protected static final String TIMESTAMP = "@timestamp";
    protected static final String HOST = "host";
    protected static final String UPS_ADV_BATTERY_RUN_TIME_REMAINING = "ups_adv_battery_run_time_remaining";
    protected static final String UPS_ADV_OUTPUT_VOLTAGE = "ups_adv_output_voltage";


    @SerializedName(UPS_ADV_OUTPUT_LOAD)
    private int outputLoad;

    @SerializedName(UPS_ADV_BATTERY_TEMPERATURE)
    private int batteryTemperature;

    @SerializedName(TIMESTAMP)
    private LocalDateTime timestamp;

    @SerializedName(HOST)
    private String host;

    private transient boolean isHostCorrect = true;

    @SerializedName(UPS_ADV_BATTERY_RUN_TIME_REMAINING)
    private int batteryRunTimeRemaining;
    private transient boolean isBatteryRunTimeRemainingCorrect = true;

    @SerializedName(UPS_ADV_OUTPUT_VOLTAGE)
    private int outputVoltage;

    private transient boolean isOutputVoltageCorrect = true;
}
