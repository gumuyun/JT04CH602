package com.zcbdqn.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用单例模式获得会话工厂SqlSessionFactory
 */
public class MybatisUtils {

    private static SqlSessionFactory factory;
    //
    private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();

    /**
     * 静态代码块,作用:初始化类的,类加载的时候执行,只执行一次
     */
    static {
        try {
            String resource = "mybatis-config.xml";
            //1.读取核心配置文件mybatis-config.xml
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //2创建SqlSessionFactory对象，读取配置文件
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得会话
     * @return
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession==null){
            sqlSession=factory.openSession(false);
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭会话
     */
    public static void closeSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession!=null){
            sqlSession.close();
        }
        threadLocal.set(null);
    }

}
