package com.example.jibriel.task1;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    CalendarManager calendarManager;
    PreferenceManager preferenceManager;

    private Button setEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceManager = new PreferenceManager();
        preferenceManager.setSharedPreferences(this);
        calendarManager = new CalendarManager(preferenceManager, this);




//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                context, MainActivity.this, startYear, starthMonth, startDay);


        setEvent = (Button) findViewById(R.id.setEventBtn);
        setEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");


            }
        });



    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        CalendarManager calendarManager;
        PreferenceManager preferenceManager;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {



            preferenceManager = new PreferenceManager();
            preferenceManager.setSharedPreferences(getContext());
            calendarManager = new CalendarManager(preferenceManager, getContext());


            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //btnDate.setText(ConverterDate.ConvertDate(year, month + 1, day));
            Toast.makeText(getContext(), year + " " + month + " " + day, Toast.LENGTH_LONG).show();
            calendarManager.getReservation();
        }
    }



//    // when button is pressed for reservation
    private void getReservation(){

        String date = "empty";
        int startTime = 0;
        int endTime = 0;

        if(calendarManager.isAvailable(startTime,endTime,date)){

            String stored = preferenceManager.getStringValue(date);

            stored = stored + "," + startTime + "," + endTime;

            preferenceManager.setStringVal(date, stored);
        }else{

            Toast.makeText(this, "Time Invalid!!",
                    Toast.LENGTH_LONG).show();

        }
    }




}
