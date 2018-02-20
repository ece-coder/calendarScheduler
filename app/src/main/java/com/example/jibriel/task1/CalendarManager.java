package com.example.jibriel.task1;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jibriel on 19/02/2018.
 */

public class CalendarManager {


    PreferenceManager preferenceManager;
    Context context;

    public CalendarManager(PreferenceManager pref, Context context) {

        preferenceManager = pref;
        this.context = context;

    }


    public boolean isAvailable(int start, int end, String key){

        String timeVal = preferenceManager.getStringValue(key);


        int commas = 0;
        for(int i = 0; i < timeVal.length(); i++) {
            if(timeVal.charAt(i) == ',') commas++;
        }

        List<String> number = Arrays.asList(timeVal.split(","));


        for (int i = 0; i < commas; i+=2){

            if (start  == Integer.valueOf(number.get(i))){
                return false;
            }
            else if(start < Integer.valueOf(number.get(i))){

                if(end >= Integer.valueOf(number.get(i))){

                    return false;
                }
            }
            else if(start > Integer.valueOf(number.get(i))){

                if(end >= Integer.valueOf(number.get(i))){

                    return false;
                }

            }

//            else{
//                if(end >= Integer.valueOf(number.get(i))){
//
//                    return false;
//                }
//            }
        }


        return true;

    }


    public void getReservation(){

        String date = "empty";
        int startTime = 0;
        int endTime = 0;

        if(isAvailable(startTime,endTime,date)){

            String stored = preferenceManager.getStringValue(date);

            stored = stored + "," + startTime + "," + endTime;

            preferenceManager.setStringVal(date, stored);
        }else{

            Toast.makeText(context, "Time Invalid!!", Toast.LENGTH_LONG).show();

        }
    }


}
