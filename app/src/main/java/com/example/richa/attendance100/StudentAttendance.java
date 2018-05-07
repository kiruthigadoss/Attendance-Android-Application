package com.example.richa.attendance100;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.io.Serializable;
import java.nio.channels.InterruptedByTimeoutException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import android.net.Uri;


public class StudentAttendance extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener {
    Spinner studentattendance_ed_attendance;
    TextView studentattendance_ed_rollno, studentattendancedate,studentattendancephone;
    Button studentattendance_bt_save, date;

    RecyclerView studentattendancerecyclerview;
    private ArrayList<StudentAttendanceModel> studentattendancelist;
    private Studentattendancelistadpter studentattendancelistadapter;
    Context context;
    String[] attendance = {"A", "P",};
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        studentattendancedate = (TextView) findViewById(R.id.studentattendance_ed_date);
        studentattendance_ed_rollno = (TextView) findViewById(R.id.studentattendance_ed_rollno);
        studentattendance_ed_attendance = (Spinner) findViewById(R.id.studentattendance_ed_attendance);
        studentattendance_ed_attendance.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, attendance);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentattendance_ed_attendance.setAdapter(aa);


        SharedPreferences preff = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
        String rollno = preff.getString("rollnoo", null);
        studentattendance_ed_rollno.setText(rollno);



        studentattendancedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        connectview();
        reloadData();


    }

    public void connectview() {
        studentattendancedate = (TextView) findViewById(R.id.studentattendance_ed_date);
        studentattendance_ed_attendance = (Spinner) findViewById(R.id.studentattendance_ed_attendance);
        studentattendance_ed_rollno = (TextView) findViewById(R.id.studentattendance_ed_rollno);
        studentattendancerecyclerview = (RecyclerView) findViewById(R.id.studentattendance_ed_recyclerview);
        studentattendancelistadapter = new Studentattendancelistadpter(context, studentattendancelist = new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        studentattendancerecyclerview.setLayoutManager(layoutManager);
        studentattendancerecyclerview.setAdapter(studentattendancelistadapter);
        studentattendance_bt_save = (Button) findViewById(R.id.studentattendance_btn_save);

        studentattendance_bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = studentattendancedate.getText().toString();
                if (TextUtils.isEmpty(p)) {
                    Toast.makeText(StudentAttendance.this, "please enter the Date", Toast.LENGTH_SHORT).show();
                } else {

                    update();
                }
            }
        });
    }

    private void add() {
        String rollno = studentattendance_ed_rollno.getText().toString().trim();
        String date = studentattendancedate.getText().toString().trim();
        String attendance = studentattendance_ed_attendance.getSelectedItem().toString().trim();

        StudentAttendanceModel studentAttendanceModel = new StudentAttendanceModel();


        studentAttendanceModel.setDate(date);
        studentAttendanceModel.setRollno(rollno);
        studentAttendanceModel.setSpin(attendance);
        studentAttendanceModel.save();

        reloadData();


    }

    private void update() {

        boolean exists = new Select().from(StudentAttendanceModel.class)
                .where("date =?", studentattendancedate.getText())
                .where("rollno=?", studentattendance_ed_rollno.getText())
                .exists();
        if (exists) {

            Toast.makeText(StudentAttendance.this, "This Date is Exist you check it once", Toast.LENGTH_SHORT).show();

        } else {
            add();

        }
    }


    private List<StudentAttendanceModel> reloadData() {
        List<StudentAttendanceModel> ls = new Select().from(StudentAttendanceModel.class).where("rollno =?", studentattendance_ed_rollno.getText()).execute();
        studentattendancelist.clear();
        studentattendancelist.addAll(ls);
        studentattendancelistadapter.notifyDataSetChanged();
        return studentattendancelist;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String date = data.getStringExtra("date");
                studentattendancedate.setText(date);

            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}

