package com.example.richa.attendance100;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.WindowManager;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Student extends AppCompatActivity implements Serializable//,Studentlistadapter.onItemClick
{
    public EditText edittextrollno, edittextname, edittextphoneno, edittextday1, edittextday2, edittextday3, edittextday4, edittextday5, edittextday6, edittextday7;
    String edlday1;
    ImageView calendar;
    TextView thedate;
    private ArrayList<StudentModel> studentlist;
    private Studentlistadapter studentlistadapter;
    Button save, submit;
    RecyclerView recyclerView;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        final ConstraintLayout base = (ConstraintLayout) findViewById(R.id.studentlo);
        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(base.getWindowToken(), 0);
            }
        });


        connectview();
        reloadData();

    }

    public void connectview() {

        edittextname = (EditText) findViewById(R.id.ed_name);
        edittextrollno = (EditText) findViewById(R.id.ed_rollno);
        edittextphoneno = (EditText) findViewById(R.id.ed_phone);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        studentlistadapter = new Studentlistadapter(context, studentlist = new ArrayList<>());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentlistadapter);
        save = (Button) findViewById(R.id.savebtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = edittextname.getText().toString();
                String m = edittextrollno.getText().toString();
                String o = edittextphoneno.getText().toString();
                if (TextUtils.isEmpty(n)) {
                    Toast.makeText(Student.this, "please enter the Rollno", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(m)) {
                    Toast.makeText(Student.this, "please enter the Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(o)) {
                    Toast.makeText(Student.this, "please enter the phonenumber", Toast.LENGTH_SHORT).show();
                } else {

                    update();
                }

            }
        });

    }

    private void add() {

        String rollno = edittextrollno.getText().toString().trim();
        String name = edittextname.getText().toString().trim();
        String phono = edittextphoneno.getText().toString().trim();
        StudentModel studentModel = new StudentModel();
        studentModel.setName(name);
        studentModel.setRollno(rollno);
        studentModel.setPhoneno(phono);
        studentModel.save();


        edittextname.setText("");
        edittextrollno.setText("");
        edittextphoneno.setText("");
        reloadData();


    }


    private List<StudentModel> reloadData() {
        List<StudentModel> ls = new Select().all().from(StudentModel.class).execute();
        studentlist.clear();
        studentlist.addAll(ls);
        studentlistadapter.notifyDataSetChanged();
        return studentlist;
    }

    private void update() {

        boolean exists = new Select().from(StudentModel.class)
                .where("rollno =?", edittextrollno.getText())
                .exists();
        if (exists) {

            Toast.makeText(Student.this, "This Rollno is Exist you check it once", Toast.LENGTH_SHORT).show();

        } else {
            add();
        }
    }


}


