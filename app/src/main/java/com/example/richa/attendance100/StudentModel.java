package com.example.richa.attendance100;

import android.support.annotation.Nullable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;

import java.io.Serializable;

/**
 * Created by anshul on 12/14/2017.
 */
@Table(name = "studenthistory")
public class StudentModel extends Model implements Serializable {

    @Column(name = "name")
    public String name;
    @Column(name = "rollno")
    public String rollno;
    @Column(name = "phoneno")
    public String phoneno;


    public String getName() {
        return name;
    }

    public StudentModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getRollno() {
        return rollno;
    }

    public StudentModel setRollno(String rollno) {
        this.rollno = rollno;
        return this;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public StudentModel setPhoneno(String phoneno) {
        this.phoneno = phoneno;
        return this;
    }


}



