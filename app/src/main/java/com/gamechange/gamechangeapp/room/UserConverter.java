package com.gamechange.gamechangeapp.room;

import android.arch.persistence.room.TypeConverter;

import com.gamechange.gamechangeapp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserConverter {

    @TypeConverter
    public static User fromString(String value) {
        Type listType = new TypeToken<String>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromClass(User value) {
        Type listType = new TypeToken<User>() {}.getType();
        return new Gson().toJson(value, listType).toString();
    }
}
