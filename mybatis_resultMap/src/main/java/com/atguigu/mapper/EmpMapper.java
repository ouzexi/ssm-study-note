package com.atguigu.mapper;

import com.atguigu.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    // 根据id查询员工信息
    Emp getEmpByEmpId(@Param("empId") Integer empId);

    // 获取员工以及所对应的部门信息
    Emp getEmpAndDeptByEmpId(@Param("empId") Integer empId);

    // 通过分步查询-查询员工以及对应的部门信息-第一步
    Emp getEmpAndDeptByStepOne(@Param("empId") Integer empId);

    // 通过分步查询-查询部门以及部门中的员工信息-第二步
    List<Emp> getDeptAndEmpByStepTwo(@Param("deptId") Integer deptId);
}
