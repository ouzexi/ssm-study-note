package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PageTest {

    /*
    * PageInfo{
    * pageNum=1, pageSize=4, size=4,
    * startRow=1, endRow=4, total=29,
    * pages=8,
    * list=Page{count=true, pageNum=1, pageSize=4, startRow=0, endRow=4, total=29, pages=8, reasonable=false, pageSizeZero=false}
    * prePage=0, nextPage=2,
    * isFirstPage=true, isLastPage=false,
    * hasPreviousPage=false, hasNextPage=true,
    * navigatePages=5, navigateFirstPage=1, navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]}
    * */
    @Test
    public void testPage() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        // 查询功能之前开启分页功能
        Page<Object> page = PageHelper.startPage(1, 4);
        List<Emp> emps = mapper.selectByExample(null);
        // 查询功能之后可以获取分页相关的所有数据
        // 返回类型的泛型是查询的所要转换的实体类类型 - navigatePages 导航分页的页码数
        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);

        // emps.forEach(System.out::println);
        System.out.println(pageInfo);
        sqlSession.close();
    }

}
