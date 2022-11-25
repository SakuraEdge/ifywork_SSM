package com.ifywork.ifywork_ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface HomeDao {

    @Select("select * from user where name= #{name}")
    Map<String,String> selectInfo(String name);

    @Select("select value from isdb where type = 'isLogin'")
    String isLogin();
}
