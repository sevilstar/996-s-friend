package com.example.yourdate2;

import android.widget.EditText;

import java.util.Date;

public class jizhang_item {
    String name;
    String date;
    String time;
    String detil;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDetil() {
        return detil;
    }

    public void setDetil(String detil) {
        this.detil = detil;
    }

    public jizhang_item()
    {

    }
    public jizhang_item(String name,String date,String time,String detil)
    {
        this.name=name;
        this.date=date;
        this.time=time;
        this.detil=detil;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time)
    {
        this.time=time;
    }
}