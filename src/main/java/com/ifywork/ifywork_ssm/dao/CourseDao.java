package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Course;
import com.ifywork.ifywork_ssm.bean.MyClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    @Select("select * from course where teacherName= '${teacherName}'")
    List<Course > selectCourse(String teacherName);

    @Insert("insert into course(name,teacherName,tag,createPerson,createTime)  " +
            "values('${name}','${teacherName}','${tag}','system','${createTime}')")
    void insertCourse(Course course);

    @Delete("delete from course where name='${name}'")
    void deleteCourse(String name);
}
