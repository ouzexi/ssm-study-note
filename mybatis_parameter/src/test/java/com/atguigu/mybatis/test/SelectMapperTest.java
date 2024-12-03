package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = mapper.getUserById(1);
        System.out.println("结果：" + user);
        sqlSession.close();
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = mapper.getAllUser();
        users.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testGetCount() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println("结果：" + count);
        sqlSession.close();
    }

    @Test
    public void testGetUserByIdToMap() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> map = mapper.getUserByIdToMap(4);
        // {password=123, id=4, username=lisi}
        // 所以map和实体类的区别是，某个属性为null，它不会放到map集合中。而实体类的属性是固定的
        // {password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin}
        System.out.println("结果：" + map);
        sqlSession.close();
    }

    @Test
    public void testGetAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> users = mapper.getAllUserToMap();
        System.out.println("结果：" + users);
        // {
        // 1={password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin},
        // 2={id=2},
        // 3={password=123456, gender=女, id=3, age=33, email=123@qq.com, username=root},
        // 4={password=123, id=4, username=lisi}
        // }
        sqlSession.close();
    }

}
