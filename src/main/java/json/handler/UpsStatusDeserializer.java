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
            upsStatus.setOutputLoad(jsonObject.get(UpsStatus.UPS_ADV_OUTPUT_LOAD).getAsInt());
        }
        catch (UnsupportedOperationException e){
        }

        try{
            upsStatus.setBatteryTemperature(jsonObject.get(UpsStatus.UPS_ADV_BATTERY_TEMPERATURE).getAsInt());
        }
        catch (UnsupportedOperationException e){
            // empty;
        }

        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            upsStatus.setTimestamp(LocalDateTime.parse(jsonObject.get(UpsStatus.TIMESTAMP).getAsString(), dateTimeFormatter));
        }
        catch (UnsupportedOperationException e){
            // empty;
        }

        try{
            upsStatus.setHost(jsonObject.get(UpsStatus.HOST).getAsString());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setHostCorrect(false);
        }

        try {
            upsStatus.setBatteryRunTimeRemaining(jsonObject.get(UpsStatus.UPS_ADV_BATTERY_RUN_TIME_REMAINING).getAsInt());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setBatteryRunTimeRemainingCorrect(false);
        }

        try {
            upsStatus.setOutputVoltage(jsonObject.get(UpsStatus.UPS_ADV_OUTPUT_VOLTAGE).getAsInt());
        }
        catch (UnsupportedOperationException e){
            upsStatus.setOutputVoltageCorrect(false);
        }

        return upsStatus;
    }
}
