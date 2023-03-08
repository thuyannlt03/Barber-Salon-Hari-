package com.example.da_1;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String MY_SHARED_PREFERENCES = " MY_SHARED_PREFERENCES";
    private Context context;

    public MySharedPreferences(Context context){
        this.context=context;
    }
    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCES, 0);
        return sharedPreferences.getBoolean(key,false);
    }
}
