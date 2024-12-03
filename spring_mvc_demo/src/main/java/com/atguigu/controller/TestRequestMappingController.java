package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
*   @RequestMapping标识一个类：设置映射请求的请求路径的初始信息
*   @RequestMapping标识一个方法：设置映射请求的请求路径的具体信息
*
*   @RequestMapping注解的value属性
*   作用：通过请求的请求路径匹配请求
*   value属性是数组类型，即当前浏览器所发送请求的请求路径匹配value属性中的任何一个值
*   则当前请求就会被注解所标识的方法进行处理
*
*   @RequestMapping注解的method属性
*   作用：通过请求的请求方式匹配请求
*   method属性是RequestMethod类型的数组，即当前浏览器所发送请求的请求方式匹配method属性中的任何一种请求方式
*   则当前请求就会被注解所标识的方法进行处理
*   若浏览器所发送的请求的请求路径和@RequestMapping注解value属性匹配，但是请求方式不匹配
*   此时页面报错：405 - Request method 'XXX' not supported
*   在@RequestMapping的基础上，结合请求方式的一些派生注解：
*   @GetMapping,@PostMapping,@DeleteMapping,@PutMapping
*
*   @RequestMapping注解的params属性
*   作用：通过请求的请求参数匹配请求，即浏览器发送的请求的请求参数必须满足params属性的设置
*   params可以使用四种表达式：
*   "param"：表示当前请求的请求参数中必须携带param参数
*   "!param"：表示当前请求的请求参数中一定不能携带param参数
*   "param=value"：表示当前请求的请求参数中必须携带param参数且值必须为value
*   "param!=value"：表示当前请求的请求参数中可以不携带param参数，若携带值一定不能是value
*
*   Parameter conditions "username" not met for actual request parameters
*
*   @RequestMapping注解的headers属性
*   作用：通过请求的请求头信息匹配请求，即浏览器发送的请求的请求头必须满足headers属性的设置
*   "header"：要求请求映射所匹配的请求必须携带header请求头信息
*   "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
*   "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
*   "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
*   若浏览器所发送的请求的请求路径和@RequestMapping注解value属性匹配，但是请求头信息不匹配
*   此时页面报错：404，即资源未找到
*
*   SpringMVC支持ant风格的路径
*   在@RequestMapping注解的value属性值中设置一些特殊字符
*   ?：任意的单个字符（不包括?）
*   *：任意个数的任意字符（不包括?和/）
*   **：任意层数的任意目录，注意使用方式只能**写在双斜线中，前后不能有任何其他字符
*
*   @RequestMapping注解使用路径中的占位符
*   传统：/deleteUser?id=1
*   rest：/user/delete/1
*   需要在@RequestMapping注解的value属性中所设置的路径中，使用{xxx}的方式表示路径中的数据
*   再通过@PathVariable注解，将占位符所标识的值和控制器方法的形参进行绑定
*
* */

@Controller
// @RequestMapping("/test")
public class TestRequestMappingController {

    // 此时控制器方法所匹配的请求路径为/test/hello
    @RequestMapping(
            value = {"/hello", "/abc"},
            method = {RequestMethod.GET, RequestMethod.POST},
            // params = {"username", "!password", "age=20", "gender!=女"}
            // referer是来源 比如从页面a到b，在b页面referer就是a的url地址
            // 请求头 和 响应头 的键不区分大小写
            headers = {"referer"}
    )
    // @GetMapping({"/hello", "/abc"})
    public String hello() {
        return "success";
    }

    @RequestMapping("/**/test/ant")
    public String testAnt() {
        return "success";
    }

    @RequestMapping("/test/rest/{username}/{id}")
    // @PathVariable("id") Integer id 意思是把路径中的id传给Integer类型的形参id
    public String testRest(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        System.out.println("id:"+id);
        System.out.println("username:"+username);
        return "success";
    }

}
