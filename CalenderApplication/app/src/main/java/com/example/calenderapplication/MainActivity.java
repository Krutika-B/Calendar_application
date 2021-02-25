package com.example.calenderapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CalendarView calender;
    TextView date_view;
    private int yr, mon, dy;
    Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // By ID we can use each component
        // which id is assign in xml file
        // use findViewById() to get the
        // CalendarView and TextView
        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);

        calender.setShowWeekNumber(true);

        final Calendar[] c = {Calendar.getInstance()};
        yr = c[0].get(Calendar.YEAR);
        mon = c[0].get(Calendar.MONTH);
        dy = c[0].get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateListener =
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int
                            monthOfYear, int dayOfMonth) {
                        selectedDate = Calendar.getInstance();
                        yr = year;
                        mon = monthOfYear;
                        dy = dayOfMonth;
                        selectedDate.set(yr, mon, dy);
                        calender.setDate(selectedDate.getTimeInMillis());
                        // Store the value of date with
                        // format in String type Variable
                        // Add 1 in month because month
                        // index is start with 0
                        String Date
                                = dy + "-"
                                + (mon + 1) + "-" + yr;

                        // set this date in TextView for Display
                        date_view.setText(Date);
                    }
                };

        date_view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.
                        this, dateListener, yr, mon, dy).show();
            }
        });


        // Add Listener in calendar
        calender
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth) {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date_view.setText(Date);
                            }
                        });
    }


    public void getWeekDaySelected(String selectedDateStr) {

        Calendar cal = Calendar.getInstance();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        // set the011, 10 - 1, 12);
        String[] arr = selectedDateStr.split("-");
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arr[2]));
        cal.set(Calendar.MONTH, (Integer.parseInt(arr[1]) - 1));
        cal.set(Calendar.YEAR, Integer.parseInt(arr[0]));
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK, first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
        first.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        // print the result
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(first.getTime()) + " -> " +
                df.format(last.getTime()));

        for (int i = 0; i < 7; i++) {
            days[i] = format.format(first.getTime());
            first.add(Calendar.DAY_OF_MONTH, 1);
            Log.d("Day of the week", days[i]);
        }


    }


}
