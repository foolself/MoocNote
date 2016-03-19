package com.example.root.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by root on 16-3-19.
 */
public class DateActivity extends Activity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Calendar cal;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
//    private int second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        setTitle(year + "-" + month + "-" + day + " " + hour + ":" + minute);

        datePicker = (DatePicker) findViewById(R.id.myDatePicker);
        timePicker = (TimePicker) findViewById(R.id.myTimePicker);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String date = i + "-" + (i1 + 1) + "-" + i2 + "";
                Log.i("tag", date);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String time = i + ":" + i1 + "";
                Log.i("tag", time);
            }
        });

        // Dialog
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = i + "-" + (i1 + 1) + "-" + i2 + "";
                Log.i("tag", date);
            }
        }, year, month, day).show();

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String time = i + ":" + i1 + "";
                Log.i("tag", time);
            }
        }, hour, minute, true).show();


    }
}
