package com.ifywork.ifywork_ssm.controller;


import com.alibaba.fastjson.JSON;
import com.ifywork.ifywork_ssm.bean.Sym;
import com.ifywork.ifywork_ssm.dao.SymDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.Reader;
import java.util.List;

@ComponentScan
@Controller
public class ListController {
    @RequestMapping("/news")
    public void getNews(HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=UTF-8");
        ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("beans.xml");
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SymDao symDao = sqlSession.getMapper(SymDao.class);

        List<Sym> symList = symDao.selectSyms();

        System.out.println(symList);
        resp.getWriter().println(JSON.toJSONString(symList));
    }
}
