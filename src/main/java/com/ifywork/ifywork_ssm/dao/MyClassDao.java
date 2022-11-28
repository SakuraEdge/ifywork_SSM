package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.MyClass;
import com.ifywork.ifywork_ssm.bean.People;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MyClassDao {
    @Insert("insert into class(classname,teacherID,maxNum,address,createPerson,createTime)  " +
            "values('${name}','${teacherID}','${maxNum}','${address}','${teacherID}','${createTime}')")
    void insertMyClass(MyClass myClass);

    @Select("select classname from class where teacherID= '${teacherID}'")
    List<String> selectClassByTeacherID(String teacherID);

    @Select("select * from 'Student_${className}' where number='${studentID}'")
    People selectStudentFromClass(@Param("className")String className, @Param("studentID")String studentID);

    @Select("select * from Student_${className}")
    List<People> selectStudentByClass(@Param("className")String className);

    @Insert("INSERT INTO student_${className}(Number,name,createPerson,createTime) " +
            "VALUES('${studentID}','${studentName}','system','${createTime}')")
    void insertStudentToClass(@Param("className")String className,@Param("studentName")String studentName,
                              @Param("studentID")String studentID,@Param("createTime") String createTime);

    @Update("create table if not exists Student_${name} (" +
            "id int not null primary key auto_increment," +
            "Number varchar(50)," +
            "name varchar(50)," +
            "createPerson varchar(50) default 'system'," +
            "createTime datetime ON UPDATE CURRENT_TIMESTAMP," +
            "updatePerson varchar(50)," +
            "updateTime datetime ON UPDATE CURRENT_TIMESTAMP" +
            ")")
    void  createClassTableOfStudent(String name);

    @Update("drop table if exists Student_${name}")
    void dropClassTable(String name);

    @Delete("delete from class where classname='${name}'")
    void deleteClass(String name);
}
