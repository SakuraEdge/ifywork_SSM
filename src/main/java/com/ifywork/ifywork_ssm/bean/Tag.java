package com.ifywork.ifywork_ssm.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Tag {
    private String name;
    private String createPerson;
    private String time;

    public Tag(String name,String createPerson) {
        this.name = name;
        this.createPerson = createPerson;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.time = formatter.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", createPerson='" + createPerson + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
