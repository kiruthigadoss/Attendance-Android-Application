package com.example.richa.attendance100;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener {

    MaterialCalendarView calendarview;


    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarview = (MaterialCalendarView) findViewById(R.id.calendarView1);
        calendarview.setSelectionColor(Color.parseColor("#FFAEC9"));


        calendarview.setOnDateChangedListener(this);


    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        String strDate = getSelectedDatesString();
        Intent iintent = new Intent(CalendarActivity.this, StudentAttendance.class);
        iintent.putExtra("date", strDate);
        setResult(Activity.RESULT_OK, iintent);
        finish();

    }

    private String getSelectedDatesString()

    {

        CalendarDay date = calendarview.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return DATE_FORMAT.format(date.getDate());

    }
}






