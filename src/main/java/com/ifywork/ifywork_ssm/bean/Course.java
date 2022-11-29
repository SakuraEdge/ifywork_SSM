package com.ifywork.ifywork_ssm.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Course {
    private String name;
    private String teacherName;
    private String tag;
    private String createTime;

    public Course(String name, String teacherName, String tag) {
        this.name = name;
        this.teacherName = teacherName;
        this.tag = tag;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.createTime = formatter.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
