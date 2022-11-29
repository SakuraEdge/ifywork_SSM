package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Knowledge;
import com.ifywork.ifywork_ssm.bean.Paper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface KnowLedgeDao {

    @Select("select * from knowledge where name = '${name}'")
    ArrayList<String> oneKnowledge(String name);

    @Insert("INSERT INTO knowledge (name,createPerson,createTime) VALUES('${name}','${createPerson}','${time}')")
    void insertKnowledge(Knowledge knowledge);

    @Select("select name from knowledge")
    ArrayList<String> selectKnowledge();

    @Select("select * from paper where knowledge = '${name}'")
    ArrayList<String> onPaper(String name);

    @Delete("delete from knowledge where name='${knowledge}'")
    void deleteKnowledge(String knowledge);

}
