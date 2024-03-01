package json.handler;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpsStatusDeserializer implements JsonDeserializer<UpsStatus> {
    @Override
    public UpsStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        UpsStatus upsStatus = new UpsStatus();

        if(!jsonElement.isJsonObject()){
            throw new JsonParseException("JsonElement must be JsonObject");
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        try{
            upsStatus.setOutputLoad(jsonObject.get("ups_adv_output_load").getAsInt());
            upsStatus.setBatteryTemperature(jsonObject.get("ups_adv_battery_temperature").getAsInt());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            upsStatus.setTimestamp(LocalDateTime.parse(jsonObject.get("@timestamp").getAsString(), dateTimeFormatter));
        }
        catch (UnsupportedOperationException e){
            // empty;
        }

        try{
            upsStatus.setHost(jsonObject.get("host").getAsString());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setHostCorrect(false);
        }

        try {
            upsStatus.setBatteryRunTimeRemaining(jsonObject.get("ups_adv_battery_run_time_remaining").getAsInt());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setBatteryRunTimeRemainingCorrect(false);
        }

        try {
            upsStatus.setOutputVoltage(jsonObject.get("ups_adv_output_voltage").getAsInt());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setOutputVoltageCorrect(false);
        }

        return upsStatus;
    }
}
