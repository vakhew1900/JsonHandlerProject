package json.handler;

import com.google.gson.*;
import lombok.Getter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpsStatusDeserializer implements JsonDeserializer<UpsStatus> {

    @Getter
    private static int fieldErrorCnt = 0;

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
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        try{
            upsStatus.setBatteryTemperature(jsonObject.get(UpsStatus.UPS_ADV_BATTERY_TEMPERATURE).getAsInt());
        }
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            upsStatus.setTimestamp(LocalDateTime.parse(jsonObject.get(UpsStatus.TIMESTAMP).getAsString(), dateTimeFormatter));
        }
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        try{
            upsStatus.setHost(jsonObject.get(UpsStatus.HOST).getAsString());
        }
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        try {
            upsStatus.setBatteryRunTimeRemaining(jsonObject.get(UpsStatus.UPS_ADV_BATTERY_RUN_TIME_REMAINING).getAsInt());
        }
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        try {
            upsStatus.setOutputVoltage(jsonObject.get(UpsStatus.UPS_ADV_OUTPUT_VOLTAGE).getAsInt());
        }
        catch (RuntimeException e){
            fieldErrorCnt++;
        }

        return upsStatus;
    }
}
