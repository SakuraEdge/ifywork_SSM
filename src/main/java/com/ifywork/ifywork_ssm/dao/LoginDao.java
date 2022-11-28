package com.ifywork.ifywork_ssm.dao;


import com.ifywork.ifywork_ssm.bean.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface LoginDao {

    @Insert("insert into user(Number,name,password,tel,createTime) values('${number}','${name}','${pwd}','${tel}','${time}')")
    void register(People people);

    @Select("select * from user where name= '${name}' and password= '${pwd}'")
    ArrayList<String> login(People people);

    @Update("update isdb set value= '${name}' where type = 'isLogin'")
    void setName(String name);

    @Select("select * from user where Number='${ID}'")
    People selectUser(String ID);
}
