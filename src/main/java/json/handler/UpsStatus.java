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
    private int advOutputLoad;

    @SerializedName("ups_adv_battery_temperature")
    private int advBatteryTemperature;

    @SerializedName("@timestamp")
    private LocalDateTime timestamp;

    @SerializedName("host")
    private String host;

    @SerializedName("ups_adv_battery_run_time_remaining")
    private int advBatteryRunTimeRemaining;

    @SerializedName("ups_adv_output_voltage")
    private int advOutputVoltage;
}
