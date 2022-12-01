package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.Course;
import com.ifywork.ifywork_ssm.bean.People;
import com.ifywork.ifywork_ssm.bean.ReplaceTRN;
import com.ifywork.ifywork_ssm.dao.CourseDao;
import com.ifywork.ifywork_ssm.dao.LoginDao;
import com.ifywork.ifywork_ssm.dao.MyClassDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan
@Controller
public class CourseController {
    @RequestMapping("/SelectCourse")
    public void selectStudentByClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //数据流获取信息
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1){
            sb.append(buf,0,len);
        }
        String str = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(str);

        String teacherName = jsonObject.getString("teacherName");
        teacherName = ReplaceTRN.replaceTRN_Instance.get(teacherName);

        List<Course> list;
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        list = courseDao.selectCourse(teacherName);

        Map<String,String> map = new HashMap<>();

        for (Course course:
                list) {
            map.put(course.getName(),course.getTag());
        }

        response.getWriter().println(JSON.toJSONString(map));

        sqlSession.close();
    }

    @RequestMapping("/AddCourse")
    public void insertCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //数据流获取信息
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1){
            sb.append(buf,0,len);
        }
        String str = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(str);

        String teachername = jsonObject.getString("teachername");
        teachername = ReplaceTRN.replaceTRN_Instance.get(teachername);

        String coursename = jsonObject.getString("coursename");
        coursename = ReplaceTRN.replaceTRN_Instance.get(coursename);

        String tag = jsonObject.getString("tag");
        tag = ReplaceTRN.replaceTRN_Instance.get(tag);

        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        courseDao.insertCourse(new Course(coursename,teachername,tag));

        response.getWriter().println(JSON.toJSONString("添加成功"));

        sqlSession.close();
    }

    @RequestMapping("/DeleteCourse")
    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //数据流获取信息
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1){
            sb.append(buf,0,len);
        }
        String str = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(str);

        String coursename = jsonObject.getString("coursename");
        coursename = ReplaceTRN.replaceTRN_Instance.get(coursename);

        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        courseDao.deleteCourse(coursename);

        response.getWriter().println(JSON.toJSONString("删除成功！"));

        sqlSession.close();
    }
}
