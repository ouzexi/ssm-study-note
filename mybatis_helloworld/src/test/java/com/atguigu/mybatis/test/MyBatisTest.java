package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public class MyBatisTest {


    @Test
    public void testInsert() throws IOException {
        // 获取核心配置文件的输入流
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//        获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
//        获取sql的会话对象SqlSession，是Mybatis提供的操作数据库的对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        获取UserMapper的代理实现类对象 --- 代理模式，这个实现类对象 底层帮助我们把映射关联好了，我们只需要执行它的接口中的方法，就能执行sql
        // 意思是UserMapper接口 不需要自己手动写实现类，直接调getMapper就能返回实现类对象，并且里面的方法帮我们把关系映射好了
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        调用mapper接口中的方法，实现添加用户信息的功能
        int result = mapper.insertUser();

//        提供sql语句的唯一标识，找到sql并执行，唯一标识是namespace.sqlId
//        int result = sqlSession.insert("com.atguigu.mybatis.pojo.mapper.UserMapper.insertUser");

        System.out.println("结果：" + result);

//        返回结果之后，需要手动提交事务
        sqlSession.commit();
//        如果想要自动提交事务
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);  --- 这个 有参方法 的参数 true 是 autoCommit
//        关闭会话
        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
        sqlSession.close();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
        sqlSession.close();
    }

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById();
        System.out.println("user：" + user);
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getAllUser();
        // System.out::println 叫做 方法引用
        users.forEach(System.out::println);
    }
}
