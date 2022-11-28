package com.ifywork.ifywork_ssm.bean;


public class ReplaceTRN {
    public static ReplaceTRN replaceTRN_Instance;

    static {
        replaceTRN_Instance = new ReplaceTRN();
    }

    public String get(String str){
        str = str.replace("\t","");
        str = str.replace("\r","");
        str = str.replace("\n","");
        return str;
    }
}
