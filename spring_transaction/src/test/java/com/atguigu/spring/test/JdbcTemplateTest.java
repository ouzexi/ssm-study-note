package com.atguigu.spring.test;

import com.atguigu.spring.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// 指定当前测试类在Spring的测试环境中执行，此时可以通过注入的方式直接获取IOC容器中的bean
@RunWith(SpringJUnit4ClassRunner.class)  // 设置测试类的运行环境
// 设置Spring测试环境的配置文件
// 注解的值就是spring配置文件名，classpath是类路径
@ContextConfiguration("classpath:spring-jdbc.xml")
public class JdbcTemplateTest {

    // 加上@Autowired注解就可以在spring-jdbc.xml找到JdbcTemplate类型的bean为成员变量jdbcTemplate自动赋值
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        String sql = "insert into t_user values(null, ?, ?, ?, ?, ?)";
        // update方法既可以实现添加又可以实现更新
        jdbcTemplate.update(sql, "root", "123", 23, "女", "123@qq.com");
    }

    @Test
    public void testGetUserById() {
        String sql = "select * from t_user where id = ?";
        // BeanPropertyRowMapper把查询出来的字段与实体类中的属性进行映射，第一个参数是sql语句，第二个参数是选择对应的实体类，之后是传给sql的参数列表
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 1);
        System.out.println(user);
    }

    @Test
    public void getAllUser() {
        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        users.forEach(System.out::println);
    }

    @Test
    public void testGetCount() {
        String sql = "select count(*) from t_user";
        // 因为是查单行单列的数据，所以不需要映射，不用传BeanPropertyRowMapper了，但是需要指定转换的类型(Integer.class)
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

}
