package com.mmc.rpilight.serialization;

import com.google.gson.Gson;

/**
 * Created by mcieplak on 2017-12-05.
 */

public class JSONAdapter implements JSON2Object{

    private Gson gson;

    public JSONAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String getJson( T object ) {
        return gson.toJson(object);
    }

    @Override
    public Object getObject(String json, Class clazz) {
        return gson.fromJson(json, clazz);
    }
}
