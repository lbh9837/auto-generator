package com.lbh.auto.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;

import java.util.Map;

/**
 * @author lbh
 * @version V1.0
 * @description: TODO
 * @date 2021/10/6 18:38
 **/
public class DatabaseMetaDataTest {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/data?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {
        Map<String, DatabaseMetaDataWrapper.ColumnsInfo> columnsInfo = getTableColumnsInfo("test_user");
        System.out.println(columnsInfo);
    }

    private static Map<String, DatabaseMetaDataWrapper.ColumnsInfo> getTableColumnsInfo(String tableName) {
        DataSourceConfig dsc = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD).build();
        try {
            return new DatabaseMetaDataWrapper(dsc.getConn())
                    .getColumnsInfo(null, dsc.getSchemaName(), tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
