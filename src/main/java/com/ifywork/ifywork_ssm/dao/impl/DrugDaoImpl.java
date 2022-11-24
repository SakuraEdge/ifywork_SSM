package com.ifywork.ifywork_ssm.dao.impl;

import com.ifywork.ifywork_ssm.bean.Drug;
import com.ifywork.ifywork_ssm.bean.Website;
import com.ifywork.ifywork_ssm.dao.DrugDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.List;

public class DrugDaoImpl implements DrugDao {

    private static final String MAPPER_PRFIX = "com.ifywork.ifywork_ssm.mapper.DrugMapper";
    private SqlSessionFactory fac;

    public DrugDaoImpl(SqlSessionFactory fac) {
        this.fac = fac;
    }

    @Override
    public List<Drug> selectDrugs() throws IOException {
        SqlSession session = null;
        session = this.fac.openSession();
        List<Drug> drugs = session.selectList(MAPPER_PRFIX + ".selectDrugs");
        return drugs;
    }

    @Override
    public Website selectDrug(int id) {
        return null;
    }

    @Override
    public void addDrug(Drug drug) {
        try (SqlSession session = this.fac.openSession()) {
            session.insert(MAPPER_PRFIX + ".addDrug", drug);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDrug(int id) {
        try (SqlSession session = this.fac.openSession()) {
            session.delete(MAPPER_PRFIX + ".deleteDrug", id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDrug(Drug drug) {
        try (SqlSession session = this.fac.openSession()) {
            session.update(MAPPER_PRFIX + ".updateDrug", drug);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
