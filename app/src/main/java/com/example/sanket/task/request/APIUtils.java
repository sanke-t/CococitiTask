package com.example.sanket.task.request;


import android.content.Context;
import android.content.SharedPreferences;

public class APIUtils {

    public static final String BASE_URL = "https://polar-crag-10136.herokuapp.com";
    public static final String SHARED_PREFERENCES_FILE_NAME = "prefs";

    public static RetroService getRetroService() {
        return RetrofitClient.getClient(BASE_URL).create(RetroService.class);
    }

    public static void updateSharedPrefs(Context context,String key, String value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getFromSharedPreferences(Context context, String key)
    {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME,context.MODE_PRIVATE).getString(key,null);
    }


}

