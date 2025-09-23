package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.exceptions.ConfigurationException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSQLSessionFactory {

   static String RESOURCE = "mybatis-config.xml";
   static SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new ConfigurationException("Invalid recourse file: " + RESOURCE);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION_FACTORY;
    }
}
