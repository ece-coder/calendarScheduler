package com.example.jibriel.task1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jibriel on 19/02/2018.
 */

public class PreferenceManager {


    SharedPreferences sharedpreferences;
    private Context parentContext;
    private static PreferenceManager preferenceManager;
    private static String PREFERENCE = "task1";


    private void setInstance(PreferenceManager settings) {
        preferenceManager = settings;
    }


    public void setSharedPreferences (Context context) {
        setInstance(this);
        parentContext = context;
        sharedpreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }


    public void setIntVal (String key, int value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }



    public int getIntValue(String key) {
        String defaultValue = "0";

        if (defaultValue == null) {
            return 0;
        }
        return sharedpreferences.getInt(key, Integer.valueOf(defaultValue));
    }


    public String getStringValue(String key){

        String defaultValue = "0";

        if (defaultValue == null) {
            return null;
        }

        return  sharedpreferences.getString(key, String.valueOf(defaultValue));

    }


    public void setStringVal (String key, String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();

    }





}
