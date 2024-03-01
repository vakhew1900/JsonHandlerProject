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

    @SerializedName("ups_adv_output_load")
    private int outputLoad;

    @SerializedName("ups_adv_battery_temperature")
    private int batteryTemperature;

    @SerializedName("@timestamp")
    private LocalDateTime timestamp;

    @SerializedName("host")
    private String host;

    private transient boolean isHostCorrect = true;

    @SerializedName("ups_adv_battery_run_time_remaining")
    private int batteryRunTimeRemaining;
    private transient boolean isBatteryRunTimeRemainingCorrect = true;

    @SerializedName("ups_adv_output_voltage")
    private int outputVoltage;

    private transient boolean isOutputVoltageCorrect = true;
}
