package com.example.departmentservice.controller;

import java.lang.reflect.Type;

import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GmtDateTypeAdapter implements JsonSerializer<Date>,JsonDeserializer<Date> {


    @Override
    public synchronized JsonElement serialize(Date date, Type type,
            JsonSerializationContext jsonSerializationContext) {
        synchronized (GmtDateTypeAdapter.class) {
            Long dateFormatAsLong = date.getTime();
            return new JsonPrimitive(dateFormatAsLong);
        }
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type,
            JsonDeserializationContext jsonDeserializationContext) {

            synchronized (GmtDateTypeAdapter.class) {
                return new Date(jsonElement.getAsLong());
            }
    }
}
