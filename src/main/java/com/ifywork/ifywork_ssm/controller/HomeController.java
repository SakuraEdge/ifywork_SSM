package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.dao.HomeDao;
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
import java.util.Map;

@ComponentScan
@Controller
public class HomeController {

    @RequestMapping("/IsLogin")
    public void IsLogin(HttpServletResponse response) throws Exception{
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        String name = "";
        HomeDao homeDao = sqlSession.getMapper(HomeDao.class);
        name = homeDao.isLogin();
        response.getWriter().println(name);

        sqlSession.close();
    }

    @RequestMapping("/Info")
    public void Info(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        String name = json.getString("name");
        name = name.replace("\r","");
        name = name.replace("\n","");

        Map<String, String> map;
        HomeDao homeDao = sqlSession.getMapper(HomeDao.class);
        map = homeDao.selectInfo(name);
        response.getWriter().println(JSON.toJSONString(map));

        sqlSession.close();
    }


}
