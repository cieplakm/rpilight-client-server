package com.mmc.rpilight.serialization;

/**
 * Created by mcieplak on 2017-12-05.
 */

public interface JSON2Object {
    <T> String getJson( T object );
    <T> T getObject(String json, Class<T> clazz);
}
