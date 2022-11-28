package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Paper;
import com.ifywork.ifywork_ssm.bean.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface TagDao {

    @Select("select * from tag where name = '${tag}'")
    ArrayList<String> oneTag(String tag);

    @Insert("INSERT INTO tag (name,createPerson,createTime) VALUES('${name}','{createPerson}','${time}')")
    void insertTag(Tag tag);

    @Select("select name from tag")
    ArrayList<String> selectTag();

    @Delete("delete from tag where name='${tag}'")
    void deleteTag(String tag);
}
