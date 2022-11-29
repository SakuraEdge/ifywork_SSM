package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.Paper;
import com.ifywork.ifywork_ssm.bean.ReplaceTRN;
import com.ifywork.ifywork_ssm.dao.LoginDao;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ComponentScan
@Controller
public class PaperController {

    @RequestMapping("/AddPaper")
    public void PaperAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        String name = jsonObject.getString("name");
        String a = jsonObject.getString("a");
        String b = jsonObject.getString("b");
        String c = jsonObject.getString("c");
        String d = jsonObject.getString("d");
        String reala = jsonObject.getString("reala");
        String knowledge = jsonObject.getString("knowledge");
        Paper paper = new Paper(name,a,b,c,d,reala,knowledge);

        PaperDao paperDao = sqlSession.getMapper(PaperDao.class);
        List<String> list = paperDao.onePaper(name);
        if (list.size()==0){
            paperDao.insertPaper(paper);
            response.getWriter().println("添加成功！");
        }
        else {
            response.getWriter().println("该试题已存在！");
        }

        sqlSession.close();
    }

    @RequestMapping("/DeletePaper")
    public void PaperDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String paper = jsonObject.getString("paper");
        paper = ReplaceTRN.replaceTRN_Instance.get(paper);
        PaperDao paperDao = sqlSession.getMapper(PaperDao.class);
        paperDao.deletePaper(paper);
        response.getWriter().println("删除成功!");

        sqlSession.close();
    }

    @RequestMapping("/SelectPaperName")
    public void SelectPaperName(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String knowledge = jsonObject.getString("knowledge");
        List<String> tags;

        PaperDao paperDao = sqlSession.getMapper(PaperDao.class);
        tags = paperDao.selectPaperName(knowledge);
        response.getWriter().println(JSON.toJSONString(tags));

        sqlSession.close();
    }

    @RequestMapping("/SelectPaper")
    public void SelectPaper(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String papername = jsonObject.getString("papername");


        PaperDao paperDao = sqlSession.getMapper(PaperDao.class);
        Paper paper = paperDao.selectPaper(papername);
        List<String> list = new ArrayList<>();
        list.add(paper.getName());
        list.add(paper.getA()+"|"+paper.getB()+"|"+paper.getC()+"|"+paper.getD()+"|"+paper.getReala());

        response.getWriter().println(JSON.toJSONString(list));
        sqlSession.close();
    }
}
