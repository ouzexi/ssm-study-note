package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
*   若sql查询的结果为多条时，一定不能以实体类类型作为方法的返回值
*   否则会抛出异常 TooManyResultsException
*   若sql查询的结果为一条时，此时可以使用实体类类型或list集合类型作为方法的返回值
* */
public interface SelectMapper {

    // 根据id查询用户信息
    User getUserById(@Param("id") Integer id);

    // 查询所有的用户信息
    List<User> getAllUser();

    // 查询用户的总数
    Integer getCount();

    // 根据id查询用户信息为map集合
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);

    // 查询所有的用户信息为map集合
    /*
    *   若查询的数据有多条时，并且要将每条数据转换为map集合
    *   此时有两种解决方案：
    *   1、将mapper接口方法的返回值设置为泛型是map的list集合
    *   List<Map<String, Object>> getAllUserToMap();
    *   结果：{password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin},{...}
    *   2、可以将每条数据转换的map集合放在一个大的map中，但是必须要通过@MapKey注解将查询的某字段的值作为大的map的值
    *   @MapKey("id")
    *   Map<String, Object> getAllUserToMap();
    *   结果：{1={password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin},2={...}}
    * */
    // List<Map<String, Object>> getAllUserToMap();

    @MapKey("id")
    Map<String, Object> getAllUserToMap();
}
