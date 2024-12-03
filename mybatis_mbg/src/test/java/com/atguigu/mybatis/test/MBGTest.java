package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MBGTest {

    @Test
    public void testMBG() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        // 根据主键id查询数据
        // Emp emp = mapper.selectByPrimaryKey(1);
        // 查询所有数据
        // List<Emp> emps = mapper.selectByExample(null);

        // 根据条件查询数据
        /*
        EmpExample example = new EmpExample();
        // QBC风格-创建条件对象-每个条件以and开头
        example.createCriteria().andEmpNameEqualTo("张三").andAgeEqualTo(20);
        // or条件 - select ... WHERE ( emp_name = ? and age = ? ) or( gender = ? )
        example.or().andGenderEqualTo("男");
        List<Emp> emps = mapper.selectByExample(example);
        */

        // 测试普通修改功能-赋值null的字段会被修改为null
        /*
        Emp emp = new Emp(1, "小黑", null, "女");
        mapper.updateByPrimaryKey(emp);
        */

        // 测试选择性修改功能--赋值null的字段不会被修改
        Emp emp = new Emp(1, "小黑", null, "女");
        mapper.updateByPrimaryKeySelective(emp);

        sqlSession.close();
    }

}
