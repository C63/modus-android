package c63.studio.fi.modus.utils;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.UUID;

public class GsonFactory {

    public static Gson create(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .registerTypeAdapter(UUID.class, new UUIDAdapter())
                .create();
    }

    public static class UUIDAdapter implements JsonSerializer<UUID>, JsonDeserializer<UUID> {
        @NonNull
        @Override
        public UUID deserialize(@NonNull final JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                String asString = json.getAsString();
                return UUID.fromString(asString);
            } catch (ClassCastException e) {
                throw new JsonParseException("Expected string", e);
            } catch (IllegalArgumentException e) {
                throw new JsonParseException("Expected UUID string", e);
            }
        }

        @NonNull
        @Override
        public JsonElement serialize(@NonNull final UUID src,
                                     @NonNull final Type typeOfSrc,
                                     @NonNull final JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

}
