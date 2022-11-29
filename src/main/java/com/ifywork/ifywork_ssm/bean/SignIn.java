package com.ifywork.ifywork_ssm.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SignIn {
    private String name;
    private String msg;
    private String date;

    public SignIn(String name, String msg) {
        this.name = name;
        this.msg = msg;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        this.date = formatter.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SignIn{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
