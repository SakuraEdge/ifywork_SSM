package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.Knowledge;
import com.ifywork.ifywork_ssm.dao.HomeDao;
import com.ifywork.ifywork_ssm.dao.KnowLedgeDao;
import com.ifywork.ifywork_ssm.dao.TagDao;
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
import java.util.List;

@ComponentScan
@Controller
public class KnowLedgeController {
    @RequestMapping("/AddKL")
    public void KLAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        String knowledge = json.getString("knowledge");

        HomeDao homeDao = sqlSession.getMapper(HomeDao.class);
        String createPerson = homeDao.isLogin();

        Knowledge knowledges = new Knowledge(knowledge,createPerson);

        KnowLedgeDao knowLedgeDao = sqlSession.getMapper(KnowLedgeDao.class);
        List<String> list = knowLedgeDao.oneKnowledge(knowledge);
        if (list.size()==0){
            knowLedgeDao.insertKnowledge(knowledges);
            response.getWriter().println("添加成功！");
        }
        else {
            response.getWriter().println("该知识点已存在！");
        }

        sqlSession.close();
    }

    @RequestMapping("/SelectKL")
    public void SelectTag(HttpServletResponse response) throws Exception {
        //设置传值的编码
        response.setContentType("text/html;charset=UTF-8");
        Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        KnowLedgeDao knowLedgeDao = sqlSession.getMapper(KnowLedgeDao.class);
        response.getWriter().println(JSON.toJSONString(knowLedgeDao.selectKnowledge()));

        sqlSession.close();
    }

    @RequestMapping("/DeleteKL")
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
        String knowledge = jsonObject.getString("knowledge");

        KnowLedgeDao knowLedgeDao = sqlSession.getMapper(KnowLedgeDao.class);
        List<String> list = knowLedgeDao.onPaper(knowledge);
        if (list.size()==0){
            knowLedgeDao.deleteKnowledge(knowledge);
            response.getWriter().println("删除成功!");
        }
        else {
            response.getWriter().println("该知识点下存在试题，无法删除！");
        }

        sqlSession.close();
    }

}
