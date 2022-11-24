package com.ifywork.ifywork_ssm.bean;

import com.ifywork.ifywork_ssm.dao.SymDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class SymTest {


    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象，同事解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SymDao symDao = sqlSession.getMapper(SymDao.class);

        Sym sym = new Sym();
        sym.setSym_no("114514");
        sym.setSymptom("123");
        sym.setLevel(111);
        symDao.addSym(sym);

        List<Sym> symList = symDao.selectSyms();
        for (Sym syms : symList){
            System.out.println(syms);
        }
    }
}
