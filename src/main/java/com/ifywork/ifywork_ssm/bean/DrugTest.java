package com.ifywork.ifywork_ssm.bean;

import com.ifywork.ifywork_ssm.dao.DrugDao;
import com.ifywork.ifywork_ssm.dao.impl.DrugDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DrugTest {
    public static void main(String[] args) throws IOException {
        Drug drug = new Drug();
        drug.setDrug_no("123456");
        drug.setDrug_name("测试药品");
        drug.setQuanlity(11);
        drug.setUnit("支");
        drug.setMoney(10000);

        //1.读取全局配置文件
        String path = "mybatis-config.xml";
        //2.通过Resources获取配置文件流
        InputStream resourceAsStream = Resources.getResourceAsStream(path);
        //3.创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //4.通过代理模式创建代理类
        DrugDao drugDao = new DrugDaoImpl(sqlSessionFactory);

        System.out.println("正在进行查询操作....");
        List<Drug> listdrug = drugDao.selectDrugs();
        for (Drug drugs : listdrug) {
            System.out.println(drugs);
            drug.setId(drugs.getId());
        }

        drug.setId(drug.getId()+1);
        System.out.print("正在进行添加操作....");
        drugDao.addDrug(drug);
        System.out.println("操作完毕");

        System.out.print("正在进行更新操作....");
        drug.setId(drug.getId()-1);
        drugDao.updateDrug(drug);
        System.out.println("操作完毕");

        System.out.print("正在进行删除操作....");
        drugDao.deleteDrug(drug.getId()+1);
        System.out.println("操作完毕");
    }
}