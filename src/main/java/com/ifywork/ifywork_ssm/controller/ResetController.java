package com.ifywork.ifywork_ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifywork.ifywork_ssm.bean.People;
import com.ifywork.ifywork_ssm.dao.LoginDao;
import com.ifywork.ifywork_ssm.dao.ResetDao;
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

@ComponentScan
@Controller
public class ResetController {
    @RequestMapping("/PaperAddServlet")
    public void ResetPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String id = jsonObject.getString("id");
        String old_pwd = jsonObject.getString("old_pwd");
        String new_pwd = jsonObject.getString("new_pwd");

        LoginDao loginDao = sqlSession.getMapper(LoginDao.class);
        ResetDao resetDao = sqlSession.getMapper(ResetDao.class);
        People peo = new People(id,null,old_pwd,null,null);
        ArrayList<String> list = loginDao.login(peo);
        System.out.println(list.size());
        if (list.size()!=0){
            resetDao.updatePwd(id,new_pwd);
            response.getWriter().println("修改成功！");
        }
        else {
            response.getWriter().println("旧密码错误");
        }
    }
}