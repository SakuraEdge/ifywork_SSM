package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.MyClass;
import com.ifywork.ifywork_ssm.bean.People;
import com.ifywork.ifywork_ssm.bean.ReplaceTRN;
import com.ifywork.ifywork_ssm.dao.HomeDao;
import com.ifywork.ifywork_ssm.dao.MyClassDao;
import com.ifywork.ifywork_ssm.dao.PaperDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class MyClassController {
    @RequestMapping("/CreateClass")
    public void createClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1){
            sb.append(buf,0,len);
        }
        String str = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(str);

        HomeDao homeDao = sqlSession.getMapper(HomeDao.class);
        String name = homeDao.isLogin();

        String className = jsonObject.getString("name");
        Integer maxNum = jsonObject.getInteger("num");
        String address = jsonObject.getString("address");


        MyClass myClass = new MyClass(className,name,maxNum,address);


        MyClassDao myClassDao = sqlSession.getMapper(MyClassDao.class);
        myClassDao.insertMyClass(myClass);
        myClassDao.createClassTableOfStudent(className);
    }

    @RequestMapping("/SelectMyClass")
    public void selectClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String teacherName = jsonObject.getString("teacherID");
        teacherName = ReplaceTRN.replaceTRN_Instance.get(teacherName);

        List<String> list;
        MyClassDao myClassDao = sqlSession.getMapper(MyClassDao.class);
        list = myClassDao.selectClassByTeacherID(teacherName);
        response.getWriter().println(JSON.toJSONString(list));
    }

    @RequestMapping("/DeleteClass")
    public void deleteClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String classname = jsonObject.getString("classname");
        MyClassDao myClassDao = sqlSession.getMapper(MyClassDao.class);
        myClassDao.dropClassTable(classname);
        myClassDao.deleteClass(classname);
        response.getWriter().println("删除成功!");
    }

    @RequestMapping("/SelectStudent")
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

        String classname = jsonObject.getString("classname");
        classname = ReplaceTRN.replaceTRN_Instance.get(classname);

        List<People> list;
        MyClassDao myClassDao = sqlSession.getMapper(MyClassDao.class);
        list = myClassDao.selectStudentByClass(classname);

        Map<String,String> map = new HashMap<>();

        for (People person:
             list) {
            map.put(person.getName(),person.getNumber());
        }

        response.getWriter().println(JSON.toJSONString(map));
    }

//    @RequestMapping("/StudentAdd")
//    public void insertStudentToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //设置传值的编码
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("utf-8");
//        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
//        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//
//        //数据流获取信息
//        StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        char[] buf = new char[1024];
//        int len;
//        while ((len = reader.read(buf)) != -1){
//            sb.append(buf,0,len);
//        }
//        String str = sb.toString();
//        JSONObject jsonObject = JSONObject.parseObject(str);
//
//        String classname = jsonObject.getString("classname");
//        classname = ReplaceTRN.replaceTRN_Instance.get(classname);
//
//        List<People> list;
//        MyClassDao myClassDao = sqlSession.getMapper(MyClassDao.class);
//
//
//
//        response.getWriter().println(JSON.toJSONString("添加成功"));
//    }
}
