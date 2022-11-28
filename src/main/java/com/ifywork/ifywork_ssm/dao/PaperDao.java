package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Paper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

public interface PaperDao {

    @Select("select * from paper where name = '${name}'")
    ArrayList<String> onePaper(String name);

    @Insert("INSERT INTO paper (name,a,b,c,d,reala,knowledge,createPerson,createTime) VALUES('${name}','${a}','${b}','${c}','${d}','${reala}','${knowledge}','System','${createTime}')")
    void insertPaper(Paper paper);

    @Delete("delete from paper where name = '${paper}'")
    void deletePaper(String paper);

    @Select("select * from paper where knowledge = '${paper}'")
    ArrayList<String> selectPaperName(String knowledge);

    @Select("select * from paper where name= ${papername}")
    List<String> selectPaper(String papername);


}
