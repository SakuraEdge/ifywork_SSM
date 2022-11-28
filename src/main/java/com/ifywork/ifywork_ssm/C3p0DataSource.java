package com.ifywork.ifywork_ssm;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;;
public class C3p0DataSource extends UnpooledDataSourceFactory {
    public C3p0DataSource(){
        this.dataSource = new ComboPooledDataSource();
    }
}

