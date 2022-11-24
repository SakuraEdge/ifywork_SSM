package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.StudentCard;

public interface StudentCardDao {
    /**
     * 通过id查询学生信息
     * @param id
     * @return
     */
    public StudentCard selectStuCardById(int id);
}
