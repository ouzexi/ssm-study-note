package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DynamicMapperTest {

    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        Emp emp = new Emp(null, "", null, "");
        List<Emp> emps = mapper.getEmpByCondition(emp);
        emps.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        Emp emp = new Emp(null, "张三", 20, "");
        List<Emp> emps = mapper.getEmpByChoose(emp);
        emps.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testInsertMoreEmp() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        Emp emp1 = new Emp(null, "张三1", 20, "男");
        Emp emp2 = new Emp(null, "张三2", 20, "男");
        Emp emp3 = new Emp(null, "张三3", 20, "男");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        mapper.insertMoreEmp(emps);

        sqlSession.close();
    }

    @Test
    public void testDeleteMoreEmp() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        Integer[] empIds = new Integer[]{6, 7};
        mapper.deleteMoreEmp(empIds);

        sqlSession.close();
    }

}
