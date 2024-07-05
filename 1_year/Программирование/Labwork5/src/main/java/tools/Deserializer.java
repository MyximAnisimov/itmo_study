package tools;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class Deserializer implements JsonDeserializer<LocalDate>{
    @Override
public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException{
      return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
}}
