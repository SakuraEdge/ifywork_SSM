package com.ifywork.ifywork_ssm.bean;

import com.alibaba.fastjson.JSON;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Paper {
    private String name;
    private String a;
    private String b;
    private String c;
    private String d;
    private String reala;
    private String knowledge;

    private final String createTime;

    public Paper(String name, String a, String b, String c, String d, String reala, String knowledge) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.reala = reala;
        this.knowledge = knowledge;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.createTime = formatter.format(date);
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getReala() {
        return reala;
    }

    public void setReala(String reala) {
        this.reala = reala;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(a+"|"+b+"|"+c+"|"+d+"|"+reala);
        return JSON.toJSONString(list);
    }
}
