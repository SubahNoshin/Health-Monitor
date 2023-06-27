package com.example.cardiacrecorder;


import com.google.firebase.Timestamp;

public class Note {
    String cmnt, dia, systolic, heart, time, date;



    public Note() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }


    public String getCmnt() {
        return cmnt;
    }

    public void setCmnt(String cmnt) {
        this.cmnt = cmnt;
    }


}










