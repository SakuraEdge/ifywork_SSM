package com.ifywork.ifywork_ssm.dao;


import com.ifywork.ifywork_ssm.bean.Website;

import java.io.IOException;
import java.util.List;

public interface WebsiteDao {

    /**
     * 查询全部记录
     */
    List<Website> selectWebsites() throws IOException;

    /**
     * 查询单条记录
     */
    Website selectWebsiteById(int id);

    /**
     * 新增一条记录
     */
    void addWebsite(Website website);

    /**
     * 删除一条记录
     */
    void deleteWebsite(int id);

    /**
     * 修改一条记录
     */
    void updateWebsite(Website website);
}
