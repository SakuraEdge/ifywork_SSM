package com.ifywork.ifywork_ssm.dao.impl;

import com.ifywork.ifywork_ssm.bean.Website;
import com.ifywork.ifywork_ssm.dao.WebsiteDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WebsiteDaoImpl implements WebsiteDao {

    @Override
    public List<Website> selectWebsites() throws IOException {
        // 读取配置文件
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        // 根据配置文件构建SqlSessionFactory
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
        // 构建SqlSession
        SqlSession session = ssf.openSession();
        // 执行Sql语句，并返回映射结果
        List<Website> websites = session.selectList("com.ifywork.ifywork_ssm.mapper.WebsiteMapper.selectAllWebsite");
        // 提交
        session.commit();
        // 关闭
        session.close();
        return websites;
    }

    @Override
    public Website selectWebsiteById(int id) {
        return null;
    }

    @Override
    public void addWebsite(Website website) {

    }

    @Override
    public void deleteWebsite(int id) {

    }

    @Override
    public void updateWebsite(Website website) {

    }
}
