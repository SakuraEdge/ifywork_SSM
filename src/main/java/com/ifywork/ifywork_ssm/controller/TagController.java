package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.Tag;
import com.ifywork.ifywork_ssm.dao.HomeDao;
import com.ifywork.ifywork_ssm.dao.PaperDao;
import com.ifywork.ifywork_ssm.dao.TagDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TagController {
    @RequestMapping("/TagAddServlet")
    public void TagAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String tag = jsonObject.getString("tag");
        HomeDao homeDao = sqlSession.getMapper(HomeDao.class);
        String createPerson = homeDao.isLogin();
        Tag tags = new Tag(tag,createPerson);


        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        List<String> list = tagDao.oneTag(tag);
        if (list.size()==0){
            tagDao.insertTag(tags);
            response.getWriter().println("添加成功！");
        }
        else {
            response.getWriter().println("该知识点已存在！");
        }
    }

    @RequestMapping("/SelectTagServlet")
    public void SelectTag(HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        response.getWriter().println(JSON.toJSONString(tagDao.selectTag()));
    }

    @RequestMapping("/DeleteTagServlet")
    public void DeleteTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        String tag = jsonObject.getString("tag");

        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        tagDao.deleteTag(tag);
        response.getWriter().println("删除成功!");
    }
}
