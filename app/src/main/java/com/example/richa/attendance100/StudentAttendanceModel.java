package com.example.richa.attendance100;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by anshul on 2/3/2018.
 */
@Table(name = "studentattendancehistory")
public class StudentAttendanceModel extends Model implements Serializable {

    @Column(name = "rollno")
    public String rollno;
    @Column(name = "date")
    public String date;
    @Column(name = "attendance")
    public String attendance;

    public String getRollno() {
        return rollno;
    }

    public StudentAttendanceModel setRollno(String rollno) {
        this.rollno = rollno;
        return this;
    }

    public String getDate() {
        return date;
    }

    public StudentAttendanceModel setDate(String date) {
        this.date = date;
        return this;

    }

    public String getSpin() {
        return attendance;
    }

    public StudentAttendanceModel setSpin(String spin) {
        this.attendance = spin;
        return this;
    }
}
