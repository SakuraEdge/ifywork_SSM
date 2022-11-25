package com.ifywork.ifywork_ssm.dao;

import com.ifywork.ifywork_ssm.bean.Sym;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.IOException;
import java.util.List;

public interface SymDao {
    /**
     * 查询全部记录
     */
    @Select("select * from sym")
    List<Sym> selectSyms() throws IOException;

    /**
     * 查询单条记录
     */


    /**
     * 新增一条记录
     */
    @Insert("insert into sym(id,sym_no,symptom,level) values(#{id},#{sym_no},#{symptom},#{level})")
    void addSym(Sym sym);

    /**
     * 删除一条记录
     */
    @Delete("delete from sym where id = #{id}")
    void deleteSym(int id);

    /**
     * 修改一条记录
     */
    @Update("update sym set sym_no = #{name},symptom = #{symptom},level = #{level} where id = #{id}")
    void updateSym(Sym sym);
}
