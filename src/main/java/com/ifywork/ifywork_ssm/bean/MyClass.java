package com.ifywork.ifywork_ssm.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyClass {
    private String name;
    private String teacherID;
    private int maxNum;
    private String address;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyClass(String name, String teacherID, int maxNum, String address) {
        this.name = name;
        this.teacherID = teacherID;
        this.maxNum = maxNum;
        this.address = address;

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.createTime = formatter.format(date);
    }
}
