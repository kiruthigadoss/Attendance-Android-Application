package com.example.richa.attendance100;

import java.io.Serializable;

/**
 * Created by anshul on 2/3/2018.
 */

public class Studentattendancelistitem implements Serializable {
    String rollno, date, attendance;

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
