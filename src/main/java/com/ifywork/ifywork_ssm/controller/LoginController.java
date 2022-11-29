package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.People;
import com.ifywork.ifywork_ssm.dao.LoginDao;
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
import java.util.ArrayList;
import java.util.Random;

@ComponentScan
@Controller
public class LoginController {

    @RequestMapping("/Register")
    public void Reg(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        JSONObject json = JSONObject.parseObject(str);
        String name = json.getString("userName");
        String pwd = json.getString("password");
        String tel = json.getString("tel");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        String p = "^(?![A-Za-z0-9]+$)(?![a-z0-9#?!@$%^&*-.]+$)(?![A-Za-z#?!@$%^&*-.]+$)" +
                "(?![A-Z0-9#?!@$%^&*-.]+$)[a-zA-Z0-9#?!@$%^&*-.]{8,16}$";

        if(!pwd.matches(p)){
            response.getWriter().println("密码格式不正确！");
        }
        else{
            Random random = new Random();
            String num = "";
            for (int i = 0; i < 10; i++) {
                num += String.valueOf(random.nextInt(10));
            }

            People peo = new People(num,name,pwd,tel,time);

            LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
            loginDao.register(peo);

            response.getWriter().println("注册成功！你的唯一用户ID为：" + num);
        }

        sqlSession.close();
    }

    @RequestMapping("/Login")
    public void Login(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        JSONObject json = JSONObject.parseObject(str);
        String name = json.getString("userName");
        String pwd = json.getString("password");
        LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
        People peo = new People(null,name,pwd,null,null);

        ArrayList<String> list = loginDao.login(peo);
        System.out.println(list.size());
        if (list.size()!=0){
            response.getWriter().println("true");
        }
        else {
            response.getWriter().println("false");
        }

        sqlSession.close();
    }

    @RequestMapping("/SetName")
    public void SetName(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        String name = jsonObject.getString("userName");

        LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
        loginDao.setName(name);
        response.getWriter().println(name);

        sqlSession.close();
    }

}
