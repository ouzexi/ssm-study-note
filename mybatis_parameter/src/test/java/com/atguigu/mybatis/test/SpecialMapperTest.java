package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SpecialMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class SpecialMapperTest {

    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper mapper = sqlSession.getMapper(SpecialMapper.class);
        List<User> users = mapper.getUserByLike("a");
        System.out.println("结果:" + users);
        sqlSession.close();
    }

    @Test
    public void testDeleteMoreUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper mapper = sqlSession.getMapper(SpecialMapper.class);
        mapper.deleteMoreUser("7, 8");
        sqlSession.close();
    }

    @Test
    public void testGetUserList() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper mapper = sqlSession.getMapper(SpecialMapper.class);
        List<User> users = mapper.getUserList("t_user");
        System.out.println("结果：" + users);
        sqlSession.close();
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper mapper = sqlSession.getMapper(SpecialMapper.class);

        User user = new User(null, "xiaoming", "123", 23, "男", "123@qq.com");
        // 要等执行完插入后，才能在user对象中获取到id
        mapper.insertUser(user);
        System.out.println("自增的id" + user.getId());

        sqlSession.close();
    }

    @Test
    // 测试：当%%中间有？时，把它当成字符串，所以ps.setString(1, "a");报错是因为不能从第1个占位符赋值a，因为当前没有占位符。
    public void testJDBC() {
        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection("", "", "");
            // String sql = "select * from t_user where username like '%?%'";
            // PreparedStatement ps = connection.prepareStatement(sql);
            // ps.setString(1, "a");

            String sql = "insert into t_user values()";
            // 第二个参数，是否允许获取自动递增的主键
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 执行sql
            ps.executeUpdate();
            // 获取主键的结果集
            ResultSet resultSet = ps.getGeneratedKeys();
            // 获取最新的主键
            resultSet.next();
            // JDBC中当前自增的主键，第1列
            int id = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
