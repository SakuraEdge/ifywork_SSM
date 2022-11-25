package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.MyClass;
import com.ifywork.ifywork_ssm.bean.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MyClassDao {
    @Insert("insert into class(classname,classname,maxNum,address,createTime)  values(#{classname},#{classname},#{maxNum},#{address},#{createTime})")
    void insertMyClass(MyClass myClass);

    @Select("select * from class where name= ${teacherID}")
    List<MyClass> selectClassByTeacherID(String teacherID);

    @Select("select * from Student_${className} where number=${studentID}")
    void selectStudentFromClass(@Param("className")String className, @Param("studentID")String studentID);

    @Insert("INSERT INTO student_${className}(Number,name,createPerson,createTime) " +
            "VALUES(${studentID},'${studentName}','system','${createTime}')")
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
}
