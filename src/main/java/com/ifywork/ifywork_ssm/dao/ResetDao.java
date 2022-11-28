package com.ifywork.ifywork_ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ResetDao {
    @Update("update user set password='${pwd}' where Number = '${id}'")
    void updatePwd(@Param("id") String id, @Param("pwd") String pwd);

}
