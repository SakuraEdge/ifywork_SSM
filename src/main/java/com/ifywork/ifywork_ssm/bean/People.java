package com.ifywork.ifywork_ssm.bean;

import java.util.Date;

public class People {
    private String number;
    private String name;
    private String pwd;
    private String tel;

    public People(String number,String name, String pwd, String tel, String time) {
        this.number = number;
        this.name = name;
        this.pwd = pwd;
        this.tel = tel;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tel='" + tel + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
