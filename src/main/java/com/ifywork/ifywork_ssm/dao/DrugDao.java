package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Drug;
import com.ifywork.ifywork_ssm.bean.Website;

import java.io.IOException;
import java.util.List;

public interface DrugDao {

    /**
     * 查询全部记录
     */
    List<Drug> selectDrugs() throws IOException;

    /**
     * 查询单条记录
     */
    Website selectDrug(int id);

    /**
     * 新增一条记录
     */
    void addDrug(Drug drug);

    /**
     * 删除一条记录
     */
    void deleteDrug(int id);

    /**
     * 修改一条记录
     */
    void updateDrug(Drug drug);
}
