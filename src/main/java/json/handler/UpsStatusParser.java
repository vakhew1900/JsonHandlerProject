package json.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class UpsStatusParser {

    public List<UpsStatus> parse(String fileName){
        try(FileReader reader = new FileReader(fileName)) {
            TypeToken<ArrayList<UpsStatus>> typeToken = new TypeToken<>() {
            };
            List<UpsStatus> upsStatuses = getGson().fromJson(reader, typeToken);
            return  upsStatuses;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Gson getGson(){
        return new GsonBuilder()
                .registerTypeAdapter(UpsStatus.class, new UpsStatusDeserializer())
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .create();
    }
}
