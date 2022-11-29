package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ResetDao {
    @Update("update user set password='${pwd}' where Number = '${id}'")
    void updatePwd(@Param("id") String id, @Param("pwd") String pwd);

    @Select("select * from signin group by id desc")
    List<SignIn> getSignIn();

    @Insert("insert into signin (name,msg,date) values ('${name}','${msg}','${date}')")
    void signIn(SignIn signIn);

    @Select("select * from signin where name='${name}' and date='${date}'")
    List<String> oneSignIn(SignIn signIn);

}
